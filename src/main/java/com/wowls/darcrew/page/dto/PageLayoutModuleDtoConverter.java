package com.wowls.darcrew.page.dto;

import com.wowls.darcrew.page.entity.FrontPageModule;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PageLayoutModuleDtoConverter {
    public PageLayoutModuleDto convert(FrontPageModule entity) {
        if (entity == null) {
            return null;
        }

        return new PageLayoutModuleDto()
                .setId(entity.getId())
                .setOrdering(entity.getOrdering())
                .setPageable(entity.getModule().isPageable())
                .setModuleName(entity.getModule().getName())
                .setModuleType(entity.getModule().getModuleType())
                .setOrdering(entity.getOrdering())
                .setApiSpecId(entity.getModule().getApiSpecId());
    }

    public List<PageLayoutModuleDto> convert(List<FrontPageModule> entities) {
        return entities.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
