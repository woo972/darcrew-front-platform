package com.wowls.darcrew.page.converter;

import com.wowls.darcrew.page.dto.PagePropertyMappingDto;
import com.wowls.darcrew.page.entity.FrontPagePropertyMapping;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PagePropertyMappingConverter {
    public PagePropertyMappingDto convert(FrontPagePropertyMapping entity) {
        if (entity == null) {
            return null;
        }
        return new PagePropertyMappingDto()
                .setSourceModuleName(entity.getSourceModule().getName())
                .setSourcePropertyName(entity.getSourcePropertyName())
                .setTargetPropertyName(entity.getTargetPropertyName());
    }

    public List<PagePropertyMappingDto> convert(List<FrontPagePropertyMapping> entities) {
        return entities.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
