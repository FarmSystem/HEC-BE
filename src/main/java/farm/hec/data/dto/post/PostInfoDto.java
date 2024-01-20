package farm.hec.data.dto.post;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostInfoDto {
    private Long postId;
    private String postImagePath;

    @Builder
    public PostInfoDto(Long postId, String postImagePath) {
        this.postId = postId;
        this.postImagePath = postImagePath;
    }

}
