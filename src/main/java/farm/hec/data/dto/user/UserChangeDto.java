package farm.hec.data.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserChangeDto {
    private String userId;
    private String userName;
    private String userNickname;

    @Builder
    public UserChangeDto(String userId, String userName, String userNickname) {
        this.userId = userId;
        this.userName = userName;
        this.userNickname = userNickname;
    }
}
