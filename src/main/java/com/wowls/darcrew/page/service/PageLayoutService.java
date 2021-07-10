package com.wowls.darcrew.page.service;

import com.wowls.darcrew.page.dto.*;
import com.wowls.darcrew.page.entity.FrontPageModule;
import com.wowls.darcrew.support.constant.ClientOsType;
import com.wowls.darcrew.support.constant.FrontPageLoadingType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class PageLayoutService {
    final private PageVersionQueryService pageVersionQueryService;
    final private PageModuleQueryService pageModuleQueryService;
    final private PagePropertyMappingQueryService pagePropertyMappingQueryService;
    final private PageQueryService pageQueryService;
    final private PageLayoutModuleDtoConverter pageLayoutModuleDtoConverter;
//    final private FluentApiDefaultCache fluentApiDefaultCache;
//    final private PageDefaultLayoutCache pageDefaultLayoutCache;
//    final private PageVersionPushHistoryQueryService pageVersionPushHistoryQueryService;
//    final private FluentApiQueryService fluentApiQueryService;
//    final private PageVersionNewestHistoryCache pageVersionNewestHistoryCache;
//    final private PageLoadingTypeCache pageLoadingTypeCache;

    public PageLayoutDto getPageLayout(PageLayoutRequest request) {
        String pageName = request.getPageName();
        PageLayoutDefaultVersionDto pageLayoutDefaultVersionDto = getPageLayoutDefaultVersionDto(pageName);
//        FluentApiDto defaultFluentApi = getDefaultFluentApi(request);
//        PreConditions.checkState(defaultFluentApi != null, "default fluent api is not found for page "+ pageName);
        return build(pageLayoutDefaultVersionDto.getPageVersionId(),
                request.getOsType(),
                request.getAppVersion(),
//                defaultFluentApi,
                null,
                pageLayoutDefaultVersionDto.getLoadingType());
    }

    private PageLayoutDefaultVersionDto getPageLayoutDefaultVersionDto(String pageName) {
//        return pageDefaultLayoutCache.get(pageName, () -> pageVersionQueryService.getDefaultPageVersion(pageName));
        return pageVersionQueryService.getDefaultPageVersion(pageName);
    }

    public PageLayoutDto getPageLayout(PageLayoutOverrideRequest request) {
        long pageVersionId = request.getPageVersionId();
//        FluentApiDto defaultFluentApi = fluentApiQueryService.getDefualtFluentApi(pageVersionId);
//        PreConditions.checkState(defaultFluentApi != null, "default fluent api is not found for page version "+ pageVersionId);
//        return build(pageVersionId, request.getOsType(), request.getAppVersion(), defaultFluentApi);
        return build(pageVersionId, request.getOsType(), request.getAppVersion(), null);
    }

//    private FluentApiDto getDefaultFluentApi(PageLayoutRequest request) {
//        String pageNAme = request.getPageName();
//        return fluentApiDefaultCache.get(pageNAme, () -> fluentApiQueryService.getDefaultFluentApiDto(pageNAme));
//    }

    public PageLayoutV2Dto getPageLayoutWithCanaryVersion(PageLayoutOverrideRequest request) {
        FrontPageVersionPushHistory pageVersionPushHistory = getFrontPageVersionPushHistory(request);
        if(pageVersionPushHistory == null)
            || pageVersionPushHistory.getFrontPageVersionPushHistoryId() == null
                || !PushType.CANARY.equals(pageVersionPushHistory.getPushType()){
            return PageLayoutV2Dto.builder().pageLayoutDto(getPageLayout(request)).build();
        }

        return PageLayoutV2Dto.builder()
                .canaryStatus(true)
                .canaryPageLayoutDto(getPageLayout(makeCanaryVersionPageLayoutRequest(request, pageVersionPushHistory)))
                .pageLayoutDto(getPageLayout(request))
                .build();
    }

    private FrontPageVersionPushHistory getFrontPageVersionPushHistory(PageLayoutRequest request){
        return pageVersionNewestHistoryCache.get(
                request.getPageName(),
                () -> pageVersionPushHistoryQueryService.getPageVersionNewestHistory(request.getPageName()));
    }

    private PageLayoutOverrideRequest makeCanaryVersionPageLayoutRequest(
            PageLayoutRequest request, FrontPageVersionPushHistory frontPageVersionPushHistory)
    {
        PageLayoutOverrideRequest pageLayoutOverrideRequest = new PageLayoutOverrideRequest();
//        pageLayoutOverrideRequest.setPageVersionId(pageVersionPushHistory.getFrontPageVersionId());
        pageLayoutOverrideRequest.setPageVersionId(0L);
        pageLayoutOverrideRequest.setAppVersion(request.getAppVersion());
        pageLayoutOverrideRequest.setOsType(request.getOsType());
        return pageLayoutOverrideRequest;
    }

    private PageLayoutDto build(long pageVersionId,
                                ClientOsType osType,
                                String appVersion,
                                FluentApiDto defaultFluentApi) {
        return build(pageVersionId, osType, appVersion, defaultFluentApi, null);
    }

    private PageLayoutDto build(long pageVersionId,
                  ClientOsType osType,
                  String appVersion,
                  FluentApiDto defaultFluentApi,
                  FrontPageLoadingType frontPageLoadingType) {
        List<FrontPageModule> frontPageModuleList = pageModuleQueryService.getFrontPageModules(pageVersionId, osType, appVersion);
        return new PageLayoutDto()
                .setPageVersionId(pageVersionId)
                .setModules(Collections.emptyList())
                .setPropertyMappings(pagePropertyMappingQueryService.getPagePropertyMappings(pageVersionId)
//                .setFluentApiAlias(defaultFluentApi.getApiAlias())
//                .setFluentApiSpecId(defaultFluentApi.getSpecId())
                .setSections(makeSections(frontPageModuleList));

    }

    private Map<Integer, List<PageLayoutModuleDto>> makeSections(List<FrontPageModule> modules) {
        return modules.stream()
                .collect(Collectors.groupingBy(FrontPageModule::getSectionIndex))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        (entry) -> entry.getKey(),
                        (entry) -> pageLayoutModuleDtoConverter.convert(entry.getValue())
                ));
    }

    private FrontPageLoadingType getLoadingType(long pageVersionId) {
        return Optional.ofNullable(findLoadingTypeByPageVersionId(pageVersionId))
                .orElse(FrontPageLoadingType.EAGER);
    }

    private FrontPageLoadingType findLoadingTypeByPageVersionId(long pageVersionId) {
        // TODO: apply cache
        return pageVersionQueryService.getLoadingType(pageVersionId);
    }

//    public List<String> expireDefaultFluentApiCache(long providerId) {
//        List<String> pageNames = pageQueryService.getPageNamesByProviderId(providerId);
//        for (String pageName : pageNames) {
//            evict(pageName);
//        }
//        return pageNames;
//    }
//
//    @Nullable
//    private void evict(String pageName) {
//        fluentApiDefaultCache.evict(pageName);
//    }
}
