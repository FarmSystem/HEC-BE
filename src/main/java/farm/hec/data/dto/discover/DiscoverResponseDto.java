package farm.hec.data.dto.discover;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
public class DiscoverResponseDto {
    private Long discoverId;
    private Integer categoryId;
    private Integer commonId;
    private Date discoverDate;
    private String discoverLocation;
    private String discoverImagePath;

    @Builder
    public DiscoverResponseDto(Long discoverId, Integer categoryId, Integer commonId, Date discoverDate, String discoverLocation, String discoverImagePath) {
        this.discoverId = discoverId;
        this.categoryId = categoryId;
        this.commonId = commonId;
        this.discoverDate = discoverDate;
        this.discoverLocation = discoverLocation;
        this.discoverImagePath = discoverImagePath;
    }
}
