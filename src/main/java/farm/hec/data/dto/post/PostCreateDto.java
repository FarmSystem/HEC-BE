package farm.hec.data.dto.post;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostCreateDto {
    private Long discoverId;
    private String content;

    @Builder
    public PostCreateDto(Long discoverId, String content) {
        this.discoverId = discoverId;
        this.content = content;
    }
}
