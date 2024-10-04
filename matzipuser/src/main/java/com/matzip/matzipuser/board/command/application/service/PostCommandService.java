package com.matzip.matzipuser.board.command.application.service;


import com.matzip.matzipuser.board.command.application.dto.PostAndTagRequestDTO;
import com.matzip.matzipuser.board.command.application.dto.TagDTO;
import com.matzip.matzipuser.board.command.domain.aggregate.Post;
import com.matzip.matzipuser.board.command.domain.aggregate.PostTag;
import com.matzip.matzipuser.board.command.domain.aggregate.Tag;
import com.matzip.matzipuser.board.command.domain.repository.PostRepository;
import com.matzip.matzipuser.board.command.domain.repository.PostTagRepository;
import com.matzip.matzipuser.board.command.domain.repository.TagRepository;
import com.matzip.matzipuser.common.util.CustomUserUtils;
import com.matzip.matzipuser.exception.RestApiException;
import com.matzip.matzipuser.users.command.domain.service.UserActivityDomainService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

import static com.matzip.matzipuser.exception.ErrorCode.NOT_FOUND;


@Service
@RequiredArgsConstructor
public class PostCommandService {

    private final PostRepository postRepository;
    private final PostTagRepository postTagRepository;
    private final TagRepository tagRepository;
    private final ModelMapper modelMapper;
    private final UserActivityDomainService userActivityDomainService;

    /* 1. 게시글, 태그 등록 */
    @Transactional
    public Long createPost(PostAndTagRequestDTO newPost) {

        // Authorization 에서 회원 고유번호를 가져온다. (JwtUtil 에서의 메서드 활용)
        //Long userSeq = 4L;   // 개발 테스트용
        Long userSeq = CustomUserUtils.getCurrentUserSeq();

        // DTO -> Entity
        Post post = modelMapper.map(newPost, Post.class);
        post.putUserSeq(userSeq);

        // 게시글 저장 후 Post Entity 반환
        Post savedPost = postRepository.save(post);
        Long postSeq = savedPost.getPostSeq();

        // tags, postTag 저장을 위한 함수 호출
        registerTagInfo(postSeq, newPost);

        // 회원 포인트 변경 (게시글 등록 시 5점 획득)
        userActivityDomainService.updateUserActivityPoint(userSeq, 5);

        return postSeq;
    }


    /* 2. 게시글, 태그 수정 */
    @Transactional
    public void updatePost(Long postSeq, PostAndTagRequestDTO updatedPost) {

        Post post = postRepository.findById(postSeq)
                .orElseThrow(() -> new RestApiException(NOT_FOUND));     // 조회 된 Post 엔티티가 없을 경우

        // 이미 soft delete 된 게시글의 경우
        if(post.getPostStatus().equals("delete")){
            throw new RestApiException(NOT_FOUND);
        }

        // Authorization 에서 회원 고유번호를 가져온다. (JwtUtil 에서의 메서드 활용)
        // Long userSeq = 4L;   // 개발 테스트용
        Long userSeq = CustomUserUtils.getCurrentUserSeq();

        /* 수정을 위해 엔티티 정보 변경 */
        post.updatePostDetails(
                postSeq,
                userSeq,
                updatedPost.getPostTitle(),
                updatedPost.getPostContent(),
                updatedPost.getBoardCategorySeq(),
                updatedPost.getListSeq(),
                updatedPost.getRestaurantSeq()
        );

        // 태그 등록 이전에 post_tag 테이블에 해당 게시글과 관련하여 등록된 태그 모두 삭제
        postTagRepository.deleteAllByPostSeq(postSeq);

        // tags, postTag 저장을 위한 함수 호출
        registerTagInfo(postSeq, updatedPost);

    }

    /* 3. 게시글 삭제*/
    @Transactional
    public void deletePost(Long postSeq) {

        // 전달 된 postSeq로 Post Entity 조회
        Post post = postRepository.findById(postSeq).orElseThrow(() -> new RestApiException(NOT_FOUND));

        // 이미 soft delete 된 게시글의 경우
        if(post.getPostStatus().equals("delete")){
            throw new RestApiException(NOT_FOUND);
        }

        // 게시글 Soft Delete
        postRepository.deleteById(postSeq);

        // 회원 포인트 변경 (게시글 삭제 시 5점 소멸)
        userActivityDomainService.updateUserActivityPoint(post.getPostUserSeq(), -5);

    }

    /* 게시글 등록, 수정 시 태그 목록 & 게시글 태그 저장을 위한 함수 */
    private void registerTagInfo(Long postSeq, PostAndTagRequestDTO post) {

        // 중복체크를 위한 ArrayList 선언
        ArrayList<Long> checkTag = new ArrayList<>();

        if (post.getTagName() != null) {
            for (String tagName : post.getTagName()) {

                // 태그가 이미 존재하는지 확인
                Optional<Tag> optionalTag = tagRepository.findByTagName(tagName);

                Long tagSeq;

                if(optionalTag.isEmpty()) {   // 태그가 등록되어 있지 않은 경우 (tags 테이블에 저장)
                    TagDTO tag = new TagDTO(tagName);
                    Tag newTag = modelMapper.map(tag, Tag.class);
                    Tag savedTag = tagRepository.save(newTag);
                    tagSeq = savedTag.getTagSeq();
                } else {     // 태그가 이미 등록되어있는 경우 (tags 테이블에서 tagSeq 가져온다)
                    tagSeq = optionalTag.get().getTagSeq();  // Optional에서 Tag 객체 꺼내기
                }

                if(!checkTag.contains(tagSeq)) {      // 게시물 내 동일한 태그 중복 체크
                    PostTag posttag = new PostTag(tagSeq, postSeq); // 게시글 고유번호와 함께 태그 저장
                    postTagRepository.save(posttag); // 게시글 태그를 DB에 저장
                }

                checkTag.add(tagSeq);
            }
        }
    }

}
