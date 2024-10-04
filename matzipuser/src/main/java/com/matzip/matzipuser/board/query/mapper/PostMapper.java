package com.matzip.matzipuser.board.query.mapper;

import com.matzip.matzipuser.board.query.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {

    List<PostDTO> searchPosts(int offset, Integer size, String postTitle, String userNickname);

    long countPostsBySearch(String postTitle, String userNickname);

    List<PostDTO> getPostsByCategory(int offset, Integer size, Long boardSeq);

    long countPostsByCategory(Long boardSeq);

    PostDTO getPostDetail(Long postSeq);

    List<PostTagDTO> getPostTags(Long postSeq);

    ListDTO getPostList(Long postSeq);

    RestaurantDTO getPostRestaurant(Long postSeq);

    Long getLikeCount(Long postSeq);

    List<PostCommentDTO> getPostComment(Long postSeq);

    List<String> getPopularTag(Long boardSeq);

    List<String> getTagKeywords(String tag);
}
