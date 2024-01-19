package farm.hec.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class User {
    @Id
    private String userId;

    private String userName;
    private String userNickname;
    private String userPassword;
    private String userProfileImagePath;

    @OneToMany(mappedBy = "user", orphanRemoval = true, fetch = FetchType.LAZY)
    private ArrayList<Like> likes = new ArrayList<>();

    @OneToMany(mappedBy = "user", orphanRemoval = true, fetch = FetchType.LAZY)
    private ArrayList<Discover> discovers = new ArrayList<>();

    @OneToMany(mappedBy = "user", orphanRemoval = true, fetch = FetchType.LAZY)
    private ArrayList<Post> posts = new ArrayList<>();

    @Builder
    public User(String userId, String userName, String userNickname, String userPassword, String userProfileImagePath) {
        this.userId = userId;
        this.userName = userName;
        this.userNickname = userNickname;
        this.userPassword = userPassword;
        this.userProfileImagePath = userProfileImagePath;
    }

}
