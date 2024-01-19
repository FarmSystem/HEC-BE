package farm.hec.data.dto.discover;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
public class DiscoverCreateDto {
    private Integer categoryId;
    private Integer commonId;
    private Date discoverDate;
    private String discoverLocation;

    @Builder
    public DiscoverCreateDto(Integer categoryId, Integer commonId, Date discoverDate, String discoverLocation) {
        this.categoryId = categoryId;
        this.commonId = commonId;
        this.discoverDate = discoverDate;
        this.discoverLocation = discoverLocation;
    }
}
