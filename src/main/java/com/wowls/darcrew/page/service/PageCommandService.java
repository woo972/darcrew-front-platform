package com.wowls.darcrew.page.service;

import com.google.common.base.Preconditions;
import com.wowls.darcrew.page.dto.PageDto;
import com.wowls.darcrew.page.dto.PageModuleAndPropertyMappingDtoList;
import com.wowls.darcrew.page.dto.PageModuleDto;
import com.wowls.darcrew.page.dto.PagePropertyMappingDto;
import com.wowls.darcrew.page.entity.FrontPage;
import com.wowls.darcrew.page.entity.FrontPageVersion;
import com.wowls.darcrew.page.repository.FrontPageRespository;
import com.wowls.darcrew.provider.repogitory.FrontProviderRepository;
import com.wowls.darcrew.support.constant.FrontPageLoadingType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class PageCommandService {
    private final FrontPageRespository frontPageRespository;
    private final PageModuleCommandService pageModuleCommandService;

    public Long register(PageDto pageDto) {
        FrontPage frontPage = Optional.ofNullable(frontPageRespository.findByName(pageDto.getName())).orElse(FrontPage.builder().build());
        Preconditions.checkState(frontPage.getId() == null , "inserting page is failed");
        frontPage.setName(pageDto.getName());
        frontPage.setDescription(pageDto.getDescription());
//        frontPage.setDeleted(false);
        frontPage = frontPageRespository.save(frontPage);
        return frontPage.getId();
    }

    private Long update(Long pageId, PageDto pageDto) {
        FrontPage page = frontPageRespository.findById(pageId);
        Preconditions.checkState(page != null, "updating page is failed : page is null");
        page.setName(pageDto.getName());
        page.setDescription(page.getDescription());
        page.setModifiedAt("");
        page.setModifiedBy("");
    }

    private Long updateVersionWithModulesAndPropertyMappings(FrontPage frontPage,
                                                             List<PageModuleDto> modules,
                                                             List<PagePropertyMappingDto> pagePropertyMappingDtos,
                                                             FrontPageLoadingType frontPageLoadingType) {
        FrontPageVersion frontPageVersion = pageVersionCommandService.register(frontPage, frontPageLoadingType);
        pageModuleCommandService.registerAll(modules);
        return frontPage.getId();
    }

    public void remove(Long pageId) {
        frontPageRespository.deleteById(pageId);
    }

}
