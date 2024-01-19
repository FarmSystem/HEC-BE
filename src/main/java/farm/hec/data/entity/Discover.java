package farm.hec.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class Discover {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long discoverId;
    private Integer categoryId;
    private Integer commonId;
    private Date discoverDate;
    private String discoverLocation;
    private String discoverImagePath;
}
