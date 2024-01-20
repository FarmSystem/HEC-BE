package farm.hec.controller;

import farm.hec.config.auth.PrincipalDetails;
import farm.hec.data.dto.post.PostCreateDto;
import farm.hec.data.dto.post.PostInfoDto;
import farm.hec.data.dto.post.PostResponseDto;
import farm.hec.service.PostService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@Tag(name = "Post", description = "자랑하기 관련 API, Authorization 필요")
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/{page}/{postId}")
    public ResponseEntity<List<PostInfoDto>> getPostList(final @AuthenticationPrincipal PrincipalDetails principalDetails,
                                                        final @PathVariable Integer page,
                                                         final @PathVariable Long postId) {
        return ResponseEntity.ok(postService.getPostList(principalDetails, page, postId));
    }

    @GetMapping("/view/{postId}")
    public ResponseEntity<PostResponseDto> getPost(final @AuthenticationPrincipal PrincipalDetails principalDetails,
                                                   final @PathVariable Long postId) {
        return ResponseEntity.ok(postService.getPost(principalDetails, postId));
    }

    @PostMapping("/upload")
    public ResponseEntity<PostResponseDto> createPost(final @AuthenticationPrincipal PrincipalDetails principalDetails,
                                                      final @RequestBody PostCreateDto postCreateDto) {
        return ResponseEntity.ok(postService.createPost(principalDetails, postCreateDto));
    }

    @PostMapping("/like/{postId}")
    public ResponseEntity<Void> likePost(final @AuthenticationPrincipal PrincipalDetails principalDetails,
                                         final @PathVariable Long postId) {
        postService.likePost(principalDetails, postId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<Void> deletePost(final @AuthenticationPrincipal PrincipalDetails principalDetails,
                                           final @PathVariable Long postId) {
        postService.deletePost(principalDetails, postId);
        return ResponseEntity.ok().build();
    }

}
