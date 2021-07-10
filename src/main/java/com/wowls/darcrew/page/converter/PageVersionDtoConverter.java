package com.wowls.darcrew.page.converter;

import com.wowls.darcrew.page.dto.PageVersionDto;
import com.wowls.darcrew.page.entity.FrontPageVersion;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PageVersionDtoConverter {
    public PageVersionDto convert(FrontPageVersion entity) {
        if (entity == null) {
            return null;
        }
        return new PageVersionDto()
                .setId(entity.getId())
                .setActivated(entity.isActivated())
                .setDefaultVersion(entity.isDefaultVersion())
                .setLoadingType(entity.getLoadingType())
                .setCreatedAt(entity.getCreatedAt())
                .setCreatedBy(entity.getCreatedBy())
                .setModfiedAt(entity.getModifiedAt());
    }

    public List<PageVersionDto> convert(List<FrontPageVersion> entities) {
        return entities.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
