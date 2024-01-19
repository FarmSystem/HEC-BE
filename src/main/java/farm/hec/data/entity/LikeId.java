package farm.hec.data.entity;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class LikeId implements Serializable {
    private String user;
    private Long post;
}
