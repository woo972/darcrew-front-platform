package com.wowls.darcrew.page.service;

import com.wowls.darcrew.page.converter.PageDtoConverter;
import com.wowls.darcrew.page.dto.PageDto;
import com.wowls.darcrew.page.dto.PageDtoList;
import com.wowls.darcrew.page.dto.PageSearchCondition;
import com.wowls.darcrew.page.entity.FrontPage;
import com.wowls.darcrew.page.repository.FrontPageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PageQueryService {
    private final FrontPageRepository frontPageRepository;
    private final PageDtoConverter pageDtoConverter;

    public PageDtoList getPageList(PageSearchCondition condition, Pageable pageable) {
        return pageDtoConverter.convert(frontPageRepository.findAll(condition.getSpecifications(), pageable));
    }

    public PageDto getPageById(Long id) {
        return pageDtoConverter.convert(frontPageRepository.findByIdAndDeletedFals(id));
    }

    public FrontPage getPageByVersionId(Long pageVersionId) {
        return frontPageRepository.findOne(containsPageVersionId(pageVersionId));
    }

    public boolean isNotDuplicatedName(String pageName) {
        FrontPage page = frontPageRepository.findByName(pageName);
        return page == null || page.isDeleted();
    }

    public List<String> getPageNamesByProviderId(Long providerId) {
        return frontPageRepository.findByProviderIdAndDeletedFalse(providerId).stream()
                .map(FrontPage::getName)
                .collect(Collectors.toList());
    }
}
