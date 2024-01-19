package farm.hec.controller;

import farm.hec.config.auth.PrincipalDetails;
import farm.hec.data.dto.user.UserChangeDto;
import farm.hec.data.dto.user.UserResponseDto;
import farm.hec.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@Tag(name = "User", description = "유저 관련 API, Authorization 필요")
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/info/{userId}")
    public ResponseEntity<UserResponseDto> getUserInfo(final @AuthenticationPrincipal PrincipalDetails principalDetails,
                                                       final @PathVariable String userId) {
        return ResponseEntity.ok(userService.getUserInfo(principalDetails, userId));
    }

    @PatchMapping(value = "/modify", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserResponseDto> changeUserInfo(final @AuthenticationPrincipal PrincipalDetails principalDetails,
                                                          final @RequestPart UserChangeDto userChangeDto,
                                                            final @RequestPart(required = false) MultipartFile profileImage) {
        return ResponseEntity.ok(userService.changeUserInfo(principalDetails, userChangeDto, profileImage));
    }

}
