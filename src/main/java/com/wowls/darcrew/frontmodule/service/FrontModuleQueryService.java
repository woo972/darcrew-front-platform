package com.wowls.darcrew.frontmodule.service;

import com.wowls.darcrew.frontmodule.dto.FrontModuleDto;
import com.wowls.darcrew.frontmodule.converter.FrontModuleDtoConverter;
import com.wowls.darcrew.frontmodule.dto.FrontModuleDtoList;
import com.wowls.darcrew.frontmodule.dto.ModuleSearchCriteria;
import com.wowls.darcrew.page.entity.FrontModule;
import com.wowls.darcrew.frontmodule.repogitory.FrontModuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

import static com.wowls.darcrew.frontmodule.service.FrontModuleSpecs.APP_VERSION_FETCHER;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class FrontModuleQueryService {
    private final FrontModuleRepository frontModuleRepository;
    private final FrontModuleDtoConverter frontModuleDtoConverter;
//    private final FrontProviderFluentApiRepository frontProviderFluentApiRepository;

    public boolean isUniqueName(String name) {
        return Objects.nonNull(getModule(name));
    }

    private FrontModule getModule(String name) {
        return frontModuleRepository.findByNameAndDeletedFalse(name);
    }

    public FrontModuleDtoList getModuleList(Pageable pageable, ModuleSearchCriteria moduleSearchCriteria) {
        return frontModuleDtoConverter.convertFromPage(frontModuleRepository.findAll(APP_VERSION_FETCHER, pageable));
    }

    public FrontModuleDto getModuleDto(String name) {
        return frontModuleDtoConverter.convertFromEntity(getModule(name));
    }

    public FrontModuleDtoList getModuleSearchList(Pageable pageable, ModuleSearchCriteria moduleSearchCriteria) {
//        if (Objects.nonNull(moduleSearchCriteria.getProviderId())) {
//
//        }
        return frontModuleDtoConverter.convertFromPage(frontModuleRepository.findAll(moduleSearchCriteria.getSpecification(), pageable));
    }


}
