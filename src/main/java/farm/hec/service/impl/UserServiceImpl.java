package farm.hec.service.impl;

import farm.hec.config.auth.PrincipalDetails;
import farm.hec.data.dto.user.UserChangeDto;
import farm.hec.data.dto.user.UserResponseDto;
import farm.hec.data.entity.User;
import farm.hec.data.repository.UserRepository;
import farm.hec.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDto getUserInfo(PrincipalDetails principalDetails, String userId) {
        Optional<User> user = userRepository.findByUserId(userId);
        if (user.isEmpty()) {
            throw new IllegalArgumentException("존재하지 않는 사용자입니다.");
        }
        return UserResponseDto.builder()
                .userId(user.get().getUserId())
                .userName(user.get().getUserName())
                .userProfileImagePath(user.get().getUserProfileImagePath())
                .build();
    }

    public UserResponseDto changeUserInfo(PrincipalDetails principalDetails, UserChangeDto userChangeDto, MultipartFile profileImage) {
        log.debug("[UserService] changeUserInfo : {}", userChangeDto);
        Optional<User> user = userRepository.findByUserId(principalDetails.getUsername());
        if (user.isEmpty()) {
            log.error("[UserService] changeUserInfo : 존재하지 않는 사용자입니다.");
            throw new IllegalArgumentException("존재하지 않는 사용자입니다.");
        }
        user.get().setUserName(userChangeDto.getUserName());
        user.get().setUserNickname(userChangeDto.getUserNickname());
        user.get().setUserProfileImagePath(saveProfileImage(profileImage));
        userRepository.save(user.get());
        return UserResponseDto.builder()
                .userId(user.get().getUserId())
                .userName(user.get().getUserName())
                .userProfileImagePath(user.get().getUserProfileImagePath())
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

        return path.toString();
    }
}
