package com.wowls.darcrew.page.service;

import com.sun.crypto.provider.Preconditions;
import com.wowls.darcrew.frontmodule.repogitory.FrontModuleRepository;
import com.wowls.darcrew.page.dto.PageModuleDto;
import com.wowls.darcrew.page.entity.FrontPageModule;
import com.wowls.darcrew.page.entity.FrontPageVersion;
import com.wowls.darcrew.page.repository.FrontPageModuleRepository;
import com.wowls.darcrew.version.entity.AppVersion;
import com.wowls.darcrew.version.service.AppVersionService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PageModuleCommandService {
    final private FrontPageModuleRepository pageModuleRepository;
    final private FrontModuleRepository moduleRepository;
    final private AppVersionService appVersionService;

    public void registerAll(List<PageModuleDto> pageModuleDtoList) {
        pageModuleDtoList.forEach(dto -> register(dto));
    }

    public void register(PageModuleDto pageModuleDto) {
        FrontPageModule frontPageModuleEntity = FrontPageModule.builder()
                .ordering(pageModuleDto.getOrdering())
                .osType(pageModuleDto.getOsType())
//                .pageVersion(frontPageVersion)
                .module(moduleRepository.findByName(pageModuleDto.getModuleName()))
//                .startVersion(getAppVersion(pageModuleDto.getStartAppVersion()))
//                .endVersion(getAppVersion(pageModuleDto.getEndAppVersion()))
                .sectionIndex(pageModuleDto.getSectionIndex())
                .build();
        pageModuleRepository.persist(frontPageModuleEntity);
    }

    private AppVersion getAppVersion(String versionString) {
        return StringUtils.isBlank(versionString) ? null : appVersionService.findAppVersion(versionString);
    }

    public void remove(FrontPageModule frontPageModule) {
        Preconditions.checkState(frontPageModule != null, "failed to remove page module : null");
        frontPageModule.setDeleted(true);
        pageModuleRepository.save(frontPageModule);

    }

    public void removeAll(List<FrontPageModule> frontPageModuleList) {
        if (ObjectUtils.isNotEmpty(frontPageModuleList)) {
            frontPageModuleList.forEach(this::remove);
        }
    }
}
