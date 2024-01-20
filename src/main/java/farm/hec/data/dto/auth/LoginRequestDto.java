package farm.hec.data.dto.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class LoginRequestDto {
    private String userId;
    private String userName;
    private String userNickname;
}
