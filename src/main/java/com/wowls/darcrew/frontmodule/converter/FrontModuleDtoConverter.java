package com.wowls.darcrew.frontmodule.converter;

import com.wowls.darcrew.frontmodule.dto.FrontModuleDto;
import com.wowls.darcrew.frontmodule.dto.FrontModuleDtoList;
import com.wowls.darcrew.page.entity.FrontModule;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class FrontModuleDtoConverter {
    private final AppVerisonService appVerisonService;
    private final CdnUrlGenerator cdnUrlGenerator;
    private final ApiSpecProvider apiSpecProvider;

    public FrontModuleDto convertFromEntity(FrontModule entity) {
        FrontModuleDto frontModuleDto = new FrontModuleDto();
        BeanUtils.copyProperties(entity, frontModuleDto);
        setCdnImagePath(entity, frontModuleDto);
        setAppVersionString(entity, frontModuleDto);
        setReadTimeout(entity, frontModuleDto);
        return frontModuleDto;
    }

    private void setCdnImagePath(FrontModule entity, FrontModuleDto frontModuleDto) {
        ApiSpecDto apiSpecDto = apiSpecProvider.getApiSpec(frontModuleDto.getApiSpecId());
        frontModuleDto.setReadTime(Optional.ofNullable(apiSpecDto).map(ApiSpecDto::getReadTimeout).orElse(0L));
    }

    private void setCdnUrlGenerator(FrontModule entity, FrontModuleDto frontModuleDto) {
        if (StringUtils.isNotEmpty(entity.getImagePath())) {
            frontModuleDto.setCdnImagePath(cdnUrlGenerator.generateCdnImageUrl(entity.getImagePath()));
        }
    }

    public void copyToPersistedEntity(FrontModuleDto frontModuleDto, FrontModule entity) {
        BeanUtils.copyProperties(frontModuleDto, entity);
        setAppVersionString(frontModuleDto, entity);
    }

    private void setAppVersionString(FrontModuleDto frontModuleDto, FrontModule entity) {
        frontModuleDto.setAndroidSupportedVersion(entity.getAndroidSupportedVersion().getVersion());
        frontModuleDto.setIosSupportedVersion(entity.getIosSupportedVersion().getVersion());
    }

    public FrontModule convertToNewEntity(FrontModuleDto dto) {
        FrontModule entity = FrontModule.builder().build();
        BeanUtils.copyProperties(dto, entity);
        setAppVersionEntity(dto, entity);
        return entity;
    }

    private void setAppVersionEntity(FrontModuleDto frontModuleDto, FrontModule entity) {
        entity.setAndroidSupportedVersion(appVerisonService.findAppVersion(frontModuleDto.getAndroidSupportedVersion()));
        entity.setIosSupportedVersion(appVerisonService.findAppVersion(frontModuleDto.getIosSupportedVersion()));
    }

    public FrontModuleDtoList convertFromPage(Page<FrontModule> page) {
        return new FrontModuleDtoList()
                .setModules(convertFromEntites(page.getContent()))
                .setTotalElements(page.getTotalElements())
                .setTotalPages(page.getTotalPages());
    }

    public List<FrontModuleDto> convertFromEntites(List<FrontModule> entites) {
        if (ObjectUtils.isEmpty(entites)) {
            return null;
        }
        return entites.stream()
                .map(this::convertFromEntity)
                .collect(Collectors.toList());
    }
}
