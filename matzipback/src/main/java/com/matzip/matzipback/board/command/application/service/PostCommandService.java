package com.matzip.matzipback.board.command.application.service;


import com.matzip.matzipback.board.command.application.dto.CreatePostAndTagReqDTO;
import com.matzip.matzipback.board.command.domain.aggregate.Post;
import com.matzip.matzipback.board.command.domain.aggregate.PostTag;
import com.matzip.matzipback.board.command.domain.repository.PostRepository;
import com.matzip.matzipback.board.command.domain.repository.PostTagRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PostCommandService {

    private final PostRepository postRepository;
    private final PostTagRepository postTagRepository;
    private final ModelMapper modelMapper;

    /* 1. 게시글 등록 */
    public Long createPost(CreatePostAndTagReqDTO newPost) {

        // 나중에 Authorization 에서 빼와야한다. JwtUtil 에서의 메서드 활용할 것임
        Long userSeq = 1L;
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


    /* 2. 게시글 수정 */

    /* 3. 게시글 삭제*/
    public Long deletePost(Long postSeq) {

        // 전달 된 postSeq로 Post Entity 조회
        Post post = postRepository.findById(postSeq)
                // 조회 된 Post 엔티티가 없을 경우
                .orElseThrow(() -> new EntityNotFoundException("Post not found with id: " + postSeq));

        // getter 메소드를 통해 반환해 줄 boardCategorySeq 값 삽입
        Long boardCategorySeq = post.getBoardCategorySeq();

        // 게시글 Soft Delete
        postRepository.deleteById(postSeq);

        return boardCategorySeq;

    }


}
