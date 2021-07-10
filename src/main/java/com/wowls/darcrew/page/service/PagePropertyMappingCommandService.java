package com.wowls.darcrew.page.service;

import com.google.common.base.Preconditions;
import com.wowls.darcrew.page.dto.PagePropertyMappingDto;
import com.wowls.darcrew.page.entity.FrontPagePropertyMapping;
import com.wowls.darcrew.page.entity.FrontPageVersion;
import com.wowls.darcrew.frontmodule.repogitory.FrontModuleRepository;
import com.wowls.darcrew.page.repository.FrontPagePropertyMappingRepository;
import com.wowls.darcrew.page.repository.FrontPageVersionRespository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class PagePropertyMappingCommandService {
    private final FrontPagePropertyMappingRepository frontPagePropertyMappingRepository;
    private final FrontModuleRepository frontModuleRepository;
    private final FrontPageVersionRespository frontPageVersionRespository;

    public void registerAll(List<PagePropertyMappingDto> pagePropertyMappingDtos, Long pageVersionId) {
        if (ObjectUtils.isNotEmpty(pagePropertyMappingDtos)) {
            FrontPageVersion frontPageVersion = frontPageVersionRespository.findOne(pageVersionId);
            registerAll(pagePropertyMappingDtos, frontPageVersion);
        }
    }

    private void registerAll(List<PagePropertyMappingDto> pagePropertyMappingDtos, FrontPageVersion frontPageVersion) {
        Preconditions.checkState(ObjectUtils.isEmpty(frontPageVersion.getPropertyMappings()),
                "Cannot save to a page version which already have page mappings");
        if (ObjectUtils.isNotEmpty(pagePropertyMappingDtos)) {
            pagePropertyMappingDtos.forEach(pagePropertyMappingDto -> register(pagePropertyMappingDto, frontPageVersion));
        }
    }

    private void register(PagePropertyMappingDto pagePropertyMappingDto, FrontPageVersion frontPageVersion) {
        FrontPagePropertyMapping entity = FrontPagePropertyMapping.builder()
                .pageVersion(frontPageVersion)
                .sourceModule(frontModuleRepository.findByNameAndDeletedFalse(pagePropertyMappingDto.getSourceModuleName()))
                .sourcePropertyName(pagePropertyMappingDto.getSourcePropertyName())
                .targetPropertyName(pagePropertyMappingDto.getTargetPropertyName())
                .build();
    }

    public void removeAll(List<FrontPagePropertyMapping> entities) {
        if (ObjectUtils.isNotEmpty(entities)) {
            entities.forEach(this::remove);
        }
    }

    public void remove(FrontPagePropertyMapping entity) {
        Preconditions.checkState(entity != null, "failed to remove property mapping: null");
        entity.setDeleted(true);
        frontPagePropertyMappingRepository.save(entity);
    }
}
