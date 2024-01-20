package farm.hec.service.impl;

import farm.hec.config.auth.PrincipalDetails;
import farm.hec.data.dto.post.PostCreateDto;
import farm.hec.data.dto.post.PostInfoDto;
import farm.hec.data.dto.post.PostResponseDto;
import farm.hec.data.entity.Discover;
import farm.hec.data.entity.Like;
import farm.hec.data.entity.Post;
import farm.hec.data.entity.User;
import farm.hec.data.repository.DiscoverRepository;
import farm.hec.data.repository.PostRepository;
import farm.hec.data.repository.UserRepository;
import farm.hec.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final DiscoverRepository discoverRepository;

    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository, DiscoverRepository discoverRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.discoverRepository = discoverRepository;
    }

    public List<PostInfoDto> getPostList(PrincipalDetails principalDetails, Integer page, Long postId) {
        log.debug("[PostService] getPostList : {}", page);
        int pageSize = 20; // 한 페이지에 표시할 게시물 수
        Sort sort = Sort.by(Sort.Direction.DESC, "postId");
        PageRequest pageRequest = PageRequest.of(page, pageSize, sort);

        // postRepository에서 적절한 메서드를 호출하여 게시물을 조회
        // 예: postRepository.findByPostIdLessThanEqual(cursor, pageRequest)
        Page<Post> posts = postRepository.findByPostIdLessThanEqual(postId, pageRequest);

        // Post 엔티티를 PostResponseDto로 변환
        return posts.stream()
                .map(post -> new PostInfoDto(
                        post.getPostId(),
                        post.getDiscover().getDiscoverImagePath()
                )).toList();
    }

    public PostResponseDto getPost(PrincipalDetails principalDetails, Long postId) {
        log.debug("[PostService] getPost : {}", postId);
        return postRepository.findById(postId)
                .map(post -> new PostResponseDto(
                        post.getPostId(),
                        post.getUser().getUserNickname(),
                        post.getUser().getUserProfileImagePath(),
                        post.getPostContent(),
                        post.getLikes().size(),
                        post.getDiscover().getDiscoverImagePath(),
                        post.getDiscover().getDiscoverDate(),
                        post.getDiscover().getDiscoverLocation(),
                        principalDetails != null && post.getLikes().stream().anyMatch(like -> like.getUser().getUserId().equals(principalDetails.getUserId()))

                )).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));
    }
    public PostResponseDto createPost(PrincipalDetails principalDetails, PostCreateDto postCreateDto) {
        log.debug("[PostService] createPost : {}", postCreateDto);

        User user = userRepository.findByUserId(principalDetails.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
        Discover discover = discoverRepository.findById(postCreateDto.getDiscoverId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 발견(discover)입니다."));

        Post post = postRepository.save(Post.builder()
                .user(user)
                .discover(discover)
                .postContent(postCreateDto.getContent())
                .build());

        return PostResponseDto.builder()
                .postId(post.getPostId())
                .postContent(post.getPostContent())
                .postLikes(post.getLikes().size())
                .postImagePath(post.getDiscover().getDiscoverImagePath())
                .discoverDate(post.getDiscover().getDiscoverDate())
                .discoverLocation(post.getDiscover().getDiscoverLocation())
                .isLiked(false)
                .build();
    }

    @Override
    public void likePost(PrincipalDetails principalDetails, Long postId) {
        log.debug("[PostService] likePost : {}", postId);

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));

        User user = userRepository.findByUserId(principalDetails.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
        Like like = Like.builder()
                .user(user)
                        .post(post)
                        .build();


        // Like 객체를 Post의 likes 컬렉션에 추가
        post.getLikes().add(like);

        postRepository.save(post);
    }

    public void deletePost(PrincipalDetails principalDetails, Long postId) {
        log.debug("[PostService] deletePost : {}", postId);
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));
        postRepository.delete(post);
    }
}
