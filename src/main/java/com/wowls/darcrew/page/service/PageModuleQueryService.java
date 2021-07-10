package com.wowls.darcrew.page.service;

import com.wowls.darcrew.page.converter.PageModuleDtoConverter;
import com.wowls.darcrew.page.dto.PageModuleDto;
import com.wowls.darcrew.page.entity.FrontPageModule;
import com.wowls.darcrew.page.repository.FrontPageModuleRepository;
import com.wowls.darcrew.support.constant.ClientOsType;
import com.wowls.darcrew.version.entity.AppVersion;
import com.wowls.darcrew.version.service.AppVersionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class PageModuleQueryService {
    private final FrontPageModuleRepository frontPageModuleRepository;
    private final PageModuleDtoConverter pageModuleDtoConverter;
    private final AppVersionService appVersionService;

    public List<PageModuleDto> getPageModules() {
        return pageModuleDtoConverter.convert(frontPageModuleRepository.findAll(pageVersionIdEquals(versionId)));
    }
//    public List<PageModuleDto> getPageModules(long versionId) {
//        return pageModuleDtoConverter.convert(frontPageModuleRepository.findAll(pageVersionIdEquals(versionId)));
//    }

    public List<FrontPageModule> getFrontPageModules(long versionId, ClientOsType osType, String appVersion) {
        List<FrontPageModule> pageModules = findAllByParameters(versionId, osType);
        AppVersion clientAppVersion = appVersionService.createNew(appVersion);
        return pageModules.stream()
                .filter(module -> module.supports(clientAppVersion))
                .collect(Collectors.toList());
    }

    private List<FrontPageModule> findAllByParameters(long versionId, ClientOsType osType) {
        return frontPageModuleRepository.findAll(Specifications
                .where(pageVersionIdEquals(versionId))
                .and(osTypeEquals(osType))
                .and(isModuleActivated()));
    }
}
