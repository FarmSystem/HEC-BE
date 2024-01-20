package farm.hec.data.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserResponseDto {
    private String userId;
    private String userName;
    private String userNickname;
    private String userProfileImagePath;
    @Builder
    public UserResponseDto(String userId, String userName, String userNickname, String userProfileImagePath) {
        this.userId = userId;
        this.userName = userName;
        this.userNickname = userNickname;
        this.userProfileImagePath = userProfileImagePath;
    }
}
