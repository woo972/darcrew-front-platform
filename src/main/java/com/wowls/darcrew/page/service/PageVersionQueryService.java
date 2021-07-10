package com.wowls.darcrew.page.service;

import com.wowls.darcrew.page.converter.PageVersionDtoConverter;
import com.wowls.darcrew.page.converter.PageAndVersionDtoConverter;
import com.wowls.darcrew.page.dto.PageAndVersionDto;
import com.wowls.darcrew.page.dto.PageLayoutDefaultVersionDto;
import com.wowls.darcrew.page.dto.PageVersionDto;
import com.wowls.darcrew.page.entity.FrontPageVersion;
import com.wowls.darcrew.page.repository.FrontPageVersionRespository;
import com.wowls.darcrew.support.constant.FrontPageLoadingType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class PageVersionQueryService {
    private final FrontPageVersionRespository frontPageVersionRespository;
    private final PageVersionDtoConverter pageVersionDtoConverter;
    private final PageAndVersionDtoConverter pageAndVersionDtoConverter;

    public List<PageVersionDto> getPageVersion(long pageId) {
        return pageVersionDtoConverter.convert(frontPageVersionRespository.findByPageIdAndDeletedFalse(pageId));
    }

    public Long getDefaultVersionId(String pageName) {
        FrontPageVersion frontPageVersion
                = frontPageVersionRespository.findOneByPageNameAndActivatedTrueAndDefaultVErsionTrueAndDeletedFalse(pageName);
        return frontPageVersion == null ? null : frontPageVersion.getId();
    }

    public PageLayoutDefaultVersionDto getDefaultPageVersion(String pageName) {
        FrontPageVersion frontPageVersion =
                frontPageVersionRespository.findOneByPageNameAndActivatedTrueAndDefaultVErsionTrueAndDeletedFalse(pageName);
        PreConditions.checkState(frontPageVersion != null, "failed to find default version id for page %s", pageName);
        PageLayoutDefaultVersionDto pageLayoutDefaultVersionDto = new PageLayoutDefaultVersionDto()
                .setPageVersionId(frontPageVersion.getId())
                .setLoadingType(frontPageVersion.getLoadingType());
        return pageLayoutDefaultVersionDto;
    }

    public FrontPageLoadingType getLoadingType(long pageVersionId) {
        FrontPageVersion frontPageVersion = Optional.ofNullable(frontPageVersionRespository.findOne(pageVersionId));
        PreConditions.checkState(frontPageVersion != null, "failed to fine version by versionId $s", frontPageVersion);
        return frontPageVersion.getLoadingType();
    }

    public List<PageAndVersionDto> getPageAndVersionListByModuleId(long moduleId) {
        List<FrontPageVersion> frontPageVersionList = frontPageVersionRespository.findbyModuleId(moduleId);
        return pageAndVersionDtoConverter.convert(frontPageVersionList);
    }
}

