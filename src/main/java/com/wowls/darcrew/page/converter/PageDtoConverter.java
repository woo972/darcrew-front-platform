package com.wowls.darcrew.page.converter;

import com.wowls.darcrew.page.dto.PageDto;
import com.wowls.darcrew.page.dto.PageDtoList;
import com.wowls.darcrew.page.entity.FrontPage;
import com.wowls.darcrew.page.entity.FrontPageVersion;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PageDtoConverter {
    private final PageVersionDtoConverter pageVersionDtoConverter;

    public PageDto convert(FrontPage entity) {
        if (entity == null) {
            return null;
        }

//        FrontPageVersion defaultVersion = findDefaultVersion(entity);
        return new PageDto()
                .setId(entity.getId())
                .setName(entity.getName())
                .setDescription(entity.getDescription());
//                .setProviderId(entity.getProvider().getId())
//                .setProviderName(entity.getProvider().getName())
//                .setDefaultVersionId(Optional.ofNullable(defaultVersion).map(FrontPageVersion::getId).orElse(null))
//                .setDefaultVersionLoadingType(Optional.ofNullable(defaultVersion).map(FrontPageVersion::getLoadingType).orElse(null))
//                .setVersions(pageVersionDtoConverter.convert(entity.getVersions()));
    }

    private FrontPageVersion findDefaultVersion(FrontPage entity) {
        if (ObjectUtils.isEmpty(entity.getVersions())) {
            return null;
        }
        return entity.getVersions().stream()
                .filter(FrontPageVersion::isDefaultVersion)
                .findFirst()
                .orElse(null);
    }

    public List<PageDto> convert(List<FrontPage> entities) {
        return entities.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    public PageDtoList convert(Page<FrontPage> page) {
        return new PageDtoList()
                .setPages(convert(page.getContent()))
                .setTotalElements(page.getTotalElements())
                .setTotalPages(page.getTotalPages());
    }
}
