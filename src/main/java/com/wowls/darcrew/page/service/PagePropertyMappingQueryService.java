package com.wowls.darcrew.page.service;

import com.wowls.darcrew.page.converter.PagePropertyMappingConverter;
import com.wowls.darcrew.page.dto.PagePropertyMappingDto;
import com.wowls.darcrew.page.entity.FrontPagePropertyMapping;
import com.wowls.darcrew.page.repository.FrontPagePropertyMappingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class PagePropertyMappingQueryService {
    private final FrontPagePropertyMappingRepository frontPagePropertyMappingRepository;
    private final PagePropertyMappingConverter pagePropertyMappingConverter;

    public List<PagePropertyMappingDto> getPagePropertyMappings(long versionId) {
        List<FrontPagePropertyMapping> frontPagePropertyMappingList =
                frontPagePropertyMappingRepository.findByPageVersionIdAndDeletedFalse(versionId);
        return pagePropertyMappingConverter.convert(frontPagePropertyMappingList);
    }
}
