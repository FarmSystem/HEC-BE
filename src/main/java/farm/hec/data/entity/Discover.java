package farm.hec.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Builder
public class Discover {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long discoverId;
    private Integer categoryId;
    private Integer commonId;
    private Date discoverDate;
    private String discoverLocation;
    private String discoverImagePath;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
}
