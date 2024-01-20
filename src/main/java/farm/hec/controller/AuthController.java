package farm.hec.controller;

import farm.hec.data.dto.auth.LoginRequestDto;
import farm.hec.data.dto.auth.TokenResponseDto;
import farm.hec.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @GetMapping("/check/{userId}")
public ResponseEntity<Boolean> checkJoin(final @PathVariable String userId) {
        return ResponseEntity.ok(authService.checkJoin(userId));
    }

    @PostMapping(value = "/join", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> join(final @RequestPart LoginRequestDto loginRequestDto,
                                                 final @RequestPart(required = false) MultipartFile profileImage) {
        authService.join(loginRequestDto, profileImage);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok(authService.login(loginRequestDto));
    }



}
