package farm.hec.controller;

import farm.hec.config.auth.PrincipalDetails;
import farm.hec.data.dto.discover.DiscoverCreateDto;
import farm.hec.data.dto.discover.DiscoverResponseDto;
import farm.hec.service.DiscoverService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@Tag(name = "Book(Discover)", description = "도감 관련 API, Authorization 필요")
@RequestMapping("/book")
public class DiscoverController {

    private final DiscoverService discoverService;

    public DiscoverController(DiscoverService discoverService) {
        this.discoverService = discoverService;
    }

    @GetMapping("/kind/{categoryId}")
    public ResponseEntity<List<DiscoverResponseDto>> getDiscoverList(final @AuthenticationPrincipal PrincipalDetails principalDetails,
                                                                     final @PathVariable Integer categoryId) {
        return ResponseEntity.ok(discoverService.getDiscoverList(principalDetails, categoryId));
    }

    @PostMapping(value = "/upload", consumes = { "multipart/form-data", "application/json" })
    public ResponseEntity<DiscoverResponseDto> createDiscover(final @AuthenticationPrincipal PrincipalDetails principalDetails,
                                                              final @RequestPart DiscoverCreateDto discoverCreateDto,
                                                              final @RequestPart(required = true) MultipartFile discoverImage){
        return ResponseEntity.ok(discoverService.createDiscover(principalDetails, discoverCreateDto, discoverImage));
    }

}
