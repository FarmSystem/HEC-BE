package farm.hec.data.dto.post;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostCreateDto {
    private String discoverId;
    private String content;

    @Builder
    public PostCreateDto(String discoverId, String content) {
        this.discoverId = discoverId;
        this.content = content;
    }
}
