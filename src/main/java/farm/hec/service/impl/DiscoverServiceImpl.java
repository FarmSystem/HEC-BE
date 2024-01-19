package farm.hec.service.impl;

import farm.hec.config.auth.PrincipalDetails;
import farm.hec.data.dto.discover.DiscoverCreateDto;
import farm.hec.data.dto.discover.DiscoverResponseDto;
import farm.hec.data.entity.Discover;
import farm.hec.data.entity.User;
import farm.hec.data.repository.DiscoverRepository;
import farm.hec.data.repository.UserRepository;
import farm.hec.service.DiscoverService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DiscoverServiceImpl implements DiscoverService {

    private final DiscoverRepository discoverRepository;
    private final UserRepository userRepository;

    public DiscoverServiceImpl(DiscoverRepository discoverRepository, UserRepository userRepository) {
        this.discoverRepository = discoverRepository;
        this.userRepository = userRepository;
    }

    public List<DiscoverResponseDto> getDiscoverList(PrincipalDetails principalDetails, Integer categoryId) {
        log.debug("[DiscoverService] getDiscoverList : {}", categoryId);
        List<DiscoverResponseDto> discovers = discoverRepository.findAllByCategoryId(categoryId).stream()
                .map(discover -> new DiscoverResponseDto(
                        discover.getDiscoverId(),
                        discover.getCategoryId(),
                        discover.getCommonId(),
                        discover.getDiscoverDate(),
                        discover.getDiscoverLocation(),
                        discover.getDiscoverImagePath()
                        )).toList();

        return discovers;
    }
    public DiscoverResponseDto getDiscover(PrincipalDetails principalDetails, Long discoverId) {
        log.debug("[DiscoverService] getDiscover : {}", discoverId);
        return discoverRepository.findById(discoverId)
                .map(discover -> new DiscoverResponseDto(
                        discover.getDiscoverId(),
                        discover.getCategoryId(),
                        discover.getCommonId(),
                        discover.getDiscoverDate(),
                        discover.getDiscoverLocation(),
                        discover.getDiscoverImagePath()
                )).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));
    }

    public DiscoverResponseDto createDiscover(PrincipalDetails principalDetails, DiscoverCreateDto discoverCreateDto, MultipartFile file) {
        log.debug("[DiscoverService] createDiscover : {}", discoverCreateDto);
        User user = userRepository.findByUserId(principalDetails.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
        Discover discover = Discover.builder()
                .categoryId(discoverCreateDto.getCategoryId())
                .commonId(discoverCreateDto.getCommonId())
                .discoverDate(discoverCreateDto.getDiscoverDate())
                .discoverLocation(discoverCreateDto.getDiscoverLocation())
                .discoverImagePath(saveDiscoverImage(file))
                .build();
        discoverRepository.save(discover);
        return DiscoverResponseDto.builder()
                .discoverId(discover.getDiscoverId())
                .categoryId(discover.getCategoryId())
                .commonId(discover.getCommonId())
                .discoverDate(discover.getDiscoverDate())
                .discoverLocation(discover.getDiscoverLocation())
                .discoverImagePath(discover.getDiscoverImagePath())
                .build();
    }

    public String saveDiscoverImage(MultipartFile discoverImage) {
        if (discoverImage.isEmpty()) {
            log.error("[DiscoverService] saveDiscoverImage error: Failed to store empty file");
            throw new IllegalArgumentException("Failed to store empty file");
        }

        String originalFileName = discoverImage.getOriginalFilename();
        if (originalFileName == null || !originalFileName.contains(".")) {
            log.error("[DiscoverService] saveDiscoverImage error: Failed to store file without extension");
            throw new IllegalArgumentException("Failed to store file without extension");
        }
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));

        String randomFileName = UUID.randomUUID() + fileExtension;

        Path filePath = Paths.get("src/main/resources/static/images/discover/" + randomFileName);
        try {
            discoverImage.transferTo(filePath);
        } catch (IOException e) {
            log.error("[DiscoverService] saveDiscoverImage error: Failed to store file");
            throw new IllegalArgumentException("Failed to store file");
        }
        return "/images/discover/" + randomFileName;
    }



}
