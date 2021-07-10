package com.wowls.darcrew.page.converter;

import com.wowls.darcrew.page.dto.PageModuleDto;
import com.wowls.darcrew.page.entity.FrontPageModule;
import com.wowls.darcrew.version.entity.AppVersion;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PageModuleDtoConverter {
    public PageModuleDto convert(FrontPageModule entity) {
        if (entity == null) {
            return null;
        }
        return new PageModuleDto()
                .setId(entity.getId())
                .setOrdering(entity.getOrdering())
                .setPageable(entity.getModule().isPageable())
                .setActivated(entity.getModule().isActivated())
                .setOsType(entity.getOsType())
                .setModuleName(entity.getModule().getName())
                .setModuleType(entity.getModule().getModuleType())
                .setApiSpecId(entity.getModule().getApiSpecId())
                .setStartAppVersion(getVersionString(entity.getStartVersion()))
                .setSectionIndex(entity.getSectionIndex())
                .setEndAppVersion(getVersionString(entity.getEndVersion()));
    }

    public List<PageModuleDto> convert(List<FrontPageModule> entities) {
        return entities.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    private String getVersionString(AppVersion version) {
        return version == null ? null : version.getVersion();
    }
}
