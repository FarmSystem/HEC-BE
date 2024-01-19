package farm.hec.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.LazyGroup;
import org.springframework.context.annotation.Lazy;

import java.util.ArrayList;
import java.util.Date;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    private String postContent;

    @OneToMany(mappedBy = "post", orphanRemoval = true, fetch = FetchType.LAZY)
    private ArrayList<Like> likes = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "discoverId")
    private Discover discover;

    @CreationTimestamp
    private Date postCreateAt;

}
