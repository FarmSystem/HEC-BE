package farm.hec.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@IdClass(LikeId.class)
@Builder
@Table(name = "likes")
public class Like {
    @Id
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;
    @Id
    @ManyToOne
    @JoinColumn(name = "postId", referencedColumnName = "postId")
    private Post post;
}
