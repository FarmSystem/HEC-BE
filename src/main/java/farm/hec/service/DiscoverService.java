package farm.hec.service;

import farm.hec.config.auth.PrincipalDetails;
import farm.hec.data.dto.discover.DiscoverCreateDto;
import farm.hec.data.dto.discover.DiscoverResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DiscoverService {
    List<DiscoverResponseDto> getDiscoverList(PrincipalDetails principalDetails, Integer categoryId);

    DiscoverResponseDto getDiscover(PrincipalDetails principalDetails, Long discoverId);

    DiscoverResponseDto createDiscover(PrincipalDetails principalDetails, DiscoverCreateDto discoverCreateDto, MultipartFile file);

}
