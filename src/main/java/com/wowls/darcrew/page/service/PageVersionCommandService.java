package com.wowls.darcrew.page.service;

import com.google.common.base.Preconditions;
import com.wowls.darcrew.page.entity.FrontPage;
import com.wowls.darcrew.page.entity.FrontPageVersion;
import com.wowls.darcrew.page.repository.FrontPageVersionRespository;
import com.wowls.darcrew.support.constant.FrontPageLoadingType;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class PageVersionCommandService {
    private final FrontPageVersionRespository frontPageVersionRespository;
    private final PageModuleCommandService pageModuleCommandService;
    private final PagePropertyMappingCommandService pagePropertyMappingCommandService;

    public FrontPageVersion register(FrontPage frontPage) {
        return this.register(frontPage, FrontPageLoadingType.EAGER);
    }

    public FrontPageVersion register(FrontPage frontPage, FrontPageLoadingType frontPageLoadingType) {
        FrontPageVersion frontPageVersion = FrontPageVersion.builder()
                .page(frontPage)
                .activated(true)
                .loadingType(frontPageLoadingType)
                .build();
        return frontPageVersionRespository.persist(frontPageVersion);
    }

    public void updateDefaultVersion(long pageId, long versionId) {
        List<FrontPageVersion> frontPageVersionList = frontPageVersionRespository.findByPageIdAndDeletedFalse(pageId);
        Preconditions.checkState(ObjectUtils.isNotEmpty(frontPageVersionList),
                "failed to update page default version: invalid page id: %s", pageId);
        frontPageVersionList
                .forEach(frontPageVersion -> frontPageVersion.setDefaultVersion(frontPageVersion.getId() == versionId));
        frontPageVersionRespository.save(frontPageVersionList);
    }

    public void updateActivated(long versionId, boolean activated) {
        FrontPageVersion frontPageVersion = frontPageVersionRespository.findOne(versionId);
        frontPageVersion.setActivated(activated);
        frontPageVersionRespository.save(frontPageVersion);
    }

    public void removeAll(List<FrontPageVersion> frontPageVersionList) {
        if (ObjectUtils.isNotEmpty(frontPageVersionList)) {
            frontPageVersionList.forEach(this::remove);
        }
    }

    public void remove(FrontPageVersion frontPageVersion) {
        Preconditions.checkState(frontPageVersion != null, "failed to remove page version: null");
        pageModuleCommandService.removeAll(frontPageVersion.getPageModules());
        pagePropertyMappingCommandService.removeAll(frontPageVersion.getPropertyMappings());
        frontPageVersion.setDeleted(true);
        frontPageVersionRespository.save(frontPageVersion);
    }
}
