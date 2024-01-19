package farm.hec.data.dto.post;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostLikeDto {
    private Long postId;
    private Integer postLike;

    public PostLikeDto(Long postId, Integer postLike) {
        this.postId = postId;
        this.postLike = postLike;
    }
}
