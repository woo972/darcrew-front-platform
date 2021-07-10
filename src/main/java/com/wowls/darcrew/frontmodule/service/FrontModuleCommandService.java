package com.wowls.darcrew.frontmodule.service;

import com.google.common.base.Preconditions;
import com.wowls.darcrew.frontmodule.dto.FrontModuleDto;
import com.wowls.darcrew.frontmodule.converter.FrontModuleDtoConverter;
import com.wowls.darcrew.page.entity.FrontModule;
import com.wowls.darcrew.frontmodule.repogitory.FrontModuleRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@RequiredArgsConstructor
@Service
@Transactional
public class FrontModuleCommandService {
    private static final String FRONT_MODULES = "front_modules";
    private final FrontModuleRepository frontModuleRepository;
    private final FrontModuleDtoConverter frontModuleDtoConverter;
    private final CdnImageUpLoader cdnImageUpLoader;
    private final ApiSpecProvider apiSpecProvider;

    public FrontModule save(FrontModuleDto frontModuleDto) {
        if (Objects.isNull(frontModuleDto)) {
            return null;
        }

        // check readtimeout config from api forge
        ApiSpecDto apiSpecDto apiSpecProvider.getApiSpec(frontModuleDto.getApiSpecId());
        Preconditions.checkArgument(apiSpecDto.getReadTimeout() > 0
            , "Registration has been failed. Read timeout is not config on api forge.");
        FrontModule deletedModule = resurrectIfDeleted(frontModuleDto);
        FrontModule frontModule;
        if (isInsertCase(frontModuleDto, deletedModule)) {
            frontModule = frontModuleDtoConverter.convertToNewEntity(frontModuleDto);
        } else {
            frontModule = update(frontModuleDto, deletedModule);
        }

        uploadImage(frontModuleDto, frontModule);

        return frontModuleRepository.save(frontModule);
    }

    private FrontModule resurrectIfDeleted(FrontModuleDto frontModuleDto) {
        FrontModule deletedModule =  frontModuleRepository.findByNameAndDeletedTrue(frontModuleDto.getName());
        if (Objects.nonNull(deletedModule)) {
            frontModuleDto.setId(deletedModule.getId());
            frontModuleDtoConverter.copyToPersistedEntity(frontModuleDto, deletedModule);
            deletedModule.setDeleted(false);
            return deletedModule;
        }
        return null;
    }

    private FrontModule update(FrontModuleDto frontmoduleDto, FrontModule deltedModule) {
        if (Objects.nonNull(deltedModule)) {
            return deltedModule;
        }
        FrontModule frontModule = frontModuleRepository.findOne(frontmoduleDto.getId());
        frontModuleDtoConverter.copyToPersistedEntity(frontmoduleDto, frontModule);
        return frontModule;
    }

    private boolean isInsertCase(FrontModuleDto frontModuleDto, FrontModule deletedModule) {
        return Objects.isNull(frontModuleDto.getId()) && Objects.isNull(deletedModule);
    }

    private void uploadImage(FrontModuleDto frontModuleDto, FrontModule frontModule) {
        String imagePath = cdnImageUpLoader.uploadAndGetPath(frontModuleDto.getImagePath(), FRONT_MODULES);
        if (StringUtils.isNotEmpty(imagePath)) {
            frontModule.setImagePath(imagePath);
        }
    }

    public void delete(Long moduleId) {
         FrontModule frontModule = frontModuleRepository.findOne(moduleId);
        if (Objects.nonNull(frontModule)
//                && frontModule.isDeletable()) {
        ){
            frontModule.setDeleted(Boolean.TRUE);
            frontModuleRepository.save(frontModule);
        }
    }
}
