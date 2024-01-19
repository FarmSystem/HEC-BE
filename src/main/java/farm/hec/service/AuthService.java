package farm.hec.service;

import farm.hec.config.auth.PrincipalDetails;
import farm.hec.data.dto.auth.LoginRequestDto;
import farm.hec.data.dto.auth.TokenResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


public interface AuthService {
    Boolean checkJoin(String userId);
    void join(LoginRequestDto loginRequestDto, MultipartFile profileImage);
    TokenResponseDto login(LoginRequestDto loginRequestDto);
    String saveProfileImage(MultipartFile profileImage);

}
