package farm.hec.service;

import farm.hec.config.auth.PrincipalDetails;
import farm.hec.data.dto.user.UserChangeDto;
import farm.hec.data.dto.user.UserResponseDto;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    UserResponseDto getUserInfo(PrincipalDetails principalDetails, String userId);
    UserResponseDto changeUserInfo(PrincipalDetails principalDetails, UserChangeDto userChangeDto, MultipartFile profileImage);


}
