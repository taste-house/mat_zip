package com.matzip.matzipback.board.command.application.service;


import com.matzip.matzipback.board.command.application.dto.PostAndTagRequestDTO;
import com.matzip.matzipback.board.command.domain.aggregate.Post;
import com.matzip.matzipback.board.command.domain.aggregate.PostTag;
import com.matzip.matzipback.board.command.domain.repository.PostRepository;
import com.matzip.matzipback.board.command.domain.repository.PostTagRepository;
import com.matzip.matzipback.common.util.CustomUserUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class PostCommandService {

    private final PostRepository postRepository;
    private final PostTagRepository postTagRepository;
    private final ModelMapper modelMapper;

    /* 1. 게시글, 태그 등록 */
    @Transactional
    public Long createPost(PostAndTagRequestDTO newPost) {

        // 나중에 Authorization 에서 빼와야한다. JwtUtil 에서의 메서드 활용할 것임
        Long userSeq = 4L;
        //Long userSeq = CustomUserUtils.getCurrentUserSeq();
        newPost.setPostUserSeq(userSeq);

        // DTO -> Entity
        Post post = modelMapper.map(newPost, Post.class);

        // 게시글 저장 후 Post Entity 반환
        Post savedPost = postRepository.save(post);

        // 태그 리스트 저장
        if (newPost.getTagSeq() != null) {
            for (Long tagSeq : newPost.getTagSeq()) {
                PostTag posttag = new PostTag(tagSeq, savedPost.getPostSeq()); // 게시글 고유번호와 함께 태그 저장
                postTagRepository.save(posttag); // 게시글 태그를 DB에 저장
            }
        }

        return savedPost.getPostSeq();
    }


    /* 2. 게시글, 태그 수정 */
    @Transactional
    public void updatePost(Long postSeq, PostAndTagRequestDTO updatedPost) {

        Post post = postRepository.findById(postSeq)
                .orElseThrow(() -> new EntityNotFoundException("Post not found with postSeq: " + postSeq));

        // 찾아온 Entity -> DTO
        PostAndTagRequestDTO tempPost = modelMapper.map(post, PostAndTagRequestDTO.class);
        // DTO Setter Method를 활용하여 수정사항 반영
        tempPost.setPostSeq(postSeq);
        tempPost.setPostUserSeq(post.getPostUserSeq());
        tempPost.setPostTitle(updatedPost.getPostTitle());
        tempPost.setPostContent(updatedPost.getPostContent());
        tempPost.setBoardCategorySeq(updatedPost.getBoardCategorySeq());
        tempPost.setListSeq(updatedPost.getListSeq());
        tempPost.setRestaurantSeq(updatedPost.getRestaurantSeq());

        // 수정한 DTO -> Entity
        modelMapper.map(tempPost, post);

        /* 수정을 위해 엔티티 정보 변경(Entity 내 메소드 정의 최소화를 위해 주석처리) */
/*        post.updatePostDetails(
                updatedPost.getPostTitle(),
                updatedPost.getPostContent(),
                updatedPost.getBoardCategorySeq(),
                updatedPost.getListSeq(),
                updatedPost.getRestaurantSeq()
        );
*/
        // 수정 요청에 들어온 태그 리스트 저장
        if (updatedPost.getTagSeq() != null) {

            // 태그 등록 이전에 post_tag 테이블에 해당 게시글과 관련하여 등록된 태그 모두 삭제
            postTagRepository.deleteAllByPostSeq(postSeq);

            // 태그 등록
            for (Long tagSeq : updatedPost.getTagSeq()) {
                PostTag postTag = new PostTag(tagSeq, postSeq); // 게시글 고유번호와 함께 태그 저장
                postTagRepository.save(postTag); // 게시글 태그를 DB에 저장
            }
        }
    }

    /* 3. 게시글 삭제*/
    public Long deletePost(Long postSeq) {

        // 전달 된 postSeq로 Post Entity 조회
        Post post = postRepository.findById(postSeq)
                // 조회 된 Post 엔티티가 없을 경우
                .orElseThrow(() -> new EntityNotFoundException("Post not found with postSeq: " + postSeq));

        // getter 메소드를 통해 반환해 줄 boardCategorySeq 값 삽입
        Long boardCategorySeq = post.getBoardCategorySeq();

        // 게시글 Soft Delete
        postRepository.deleteById(postSeq);

        return boardCategorySeq;

    }



}
