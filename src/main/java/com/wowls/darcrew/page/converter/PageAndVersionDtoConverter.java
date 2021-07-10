package com.wowls.darcrew.page.converter;

import com.wowls.darcrew.page.dto.PageAndVersionDto;
import com.wowls.darcrew.page.entity.FrontPageVersion;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PageAndVersionDtoConverter {
    public PageAndVersionDto convert(FrontPageVersion entity) {
        if (entity == null) {
            return null;
        }

        return new PageAndVersionDto()
                .setPageId(entity.getPage().getId())
                .setPageName(entity.getPage().getName())
                .setVersionId(entity.getId())
                .setIsDefaultVersion(entity.isDefaultVersion());
    }

    public List<PageAndVersionDto> convert(List<FrontPageVersion> entities) {
        return entities.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
