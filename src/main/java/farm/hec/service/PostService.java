package farm.hec.service;

import farm.hec.config.auth.PrincipalDetails;
import farm.hec.data.dto.post.PostCreateDto;
import farm.hec.data.dto.post.PostInfoDto;
import farm.hec.data.dto.post.PostResponseDto;

import java.util.List;

public interface PostService {
    List<PostInfoDto> getPostList(PrincipalDetails principalDetails, Integer page, Long postId);
    PostResponseDto getPost(PrincipalDetails principalDetails, Long postId);
    PostResponseDto createPost(PrincipalDetails principalDetails, PostCreateDto postCreateDto);
    void likePost(PrincipalDetails principalDetails, Long postId);
    void deletePost(PrincipalDetails principalDetails, Long postId);
}
