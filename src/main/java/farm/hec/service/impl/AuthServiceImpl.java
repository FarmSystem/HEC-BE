package farm.hec.service.impl;

import farm.hec.config.auth.PrincipalDetails;
import farm.hec.config.jwt.TokenProvider;
import farm.hec.data.dto.auth.LoginRequestDto;
import farm.hec.data.dto.auth.TokenResponseDto;
import farm.hec.data.entity.User;
import farm.hec.data.repository.UserRepository;
import farm.hec.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {
    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${app.jwt.password}")
    private String jwtPassword;
    public AuthServiceImpl(TokenProvider tokenProvider, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.tokenProvider = tokenProvider;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;

    }

    public Boolean checkJoin(String userId) {
        return userRepository.existsById(userId);
    }

    public void join(LoginRequestDto loginRequestDto, MultipartFile profileImage) {
        userRepository.save(User.builder()
                .userId(loginRequestDto.getUserId())
                .userPassword(bCryptPasswordEncoder.encode(jwtPassword))
                .userName(loginRequestDto.getUserName())
                .userProfileImagePath(saveProfileImage(profileImage))
                .build());

    }

    public TokenResponseDto login(LoginRequestDto loginRequestDto) {
        User user = userRepository.findByUserId(loginRequestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
        String accessToken = tokenProvider.generateAccessToken(new PrincipalDetails(user));
        String refreshToken = tokenProvider.generateRefreshToken(new PrincipalDetails(user));

        return TokenResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public String saveProfileImage(MultipartFile profileImage) {
        if (profileImage.isEmpty()) {
            log.error("[AuthService] saveProfileImage error: Failed to store empty file");
            throw new IllegalArgumentException("Failed to store empty file");
        }

        String originalFileName = profileImage.getOriginalFilename();
        if (originalFileName == null || !originalFileName.contains(".")) {
            log.error("[AuthService] saveProfileImage error: Failed to store file without extension");
            throw new IllegalArgumentException("Failed to store file without extension");
        }
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));

        String randomFileName = UUID.randomUUID() + fileExtension;

        // 'resources/static/images/profile'가 'src/main'
        Path path = Paths.get("src/main/resources/static/images/profile/" + randomFileName);

        try {
            profileImage.transferTo(path);
        } catch (IOException e) {
            log.error("[AuthService] saveProfileImage error: {}", e.getMessage());

            throw new IllegalArgumentException("Failed to store file {}", e);
        }

        return "/images/profile/" + randomFileName;
    }

}
