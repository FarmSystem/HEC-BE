package farm.hec.data.dto.post;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostResponseDto {
    private Long postId;
    private String authorNickname;
    private String authorProfileImagePath;
    private String postContent;
    private Long postLikes;
    private String postImagePath;
    private String discoverDate;
    private String discoverLocation;
    private Boolean isLiked;

    @Builder
    public PostResponseDto(Long postId, String authorNickname, String authorProfileImagePath, String postContent, Long postLikes, String postImagePath, String discoverDate, String discoverLocation, Boolean isLiked) {
        this.postId = postId;
        this.authorNickname = authorNickname;
        this.authorProfileImagePath = authorProfileImagePath;
        this.postContent = postContent;
        this.postLikes = postLikes;
        this.postImagePath = postImagePath;
        this.discoverDate = discoverDate;
        this.discoverLocation = discoverLocation;
        this.isLiked = isLiked;
    }

}
