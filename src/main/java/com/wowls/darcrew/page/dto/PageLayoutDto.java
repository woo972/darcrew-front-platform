package com.wowls.darcrew.page.dto;

import com.wowls.darcrew.support.constant.FrontPageLoadingType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Accessors(chain = true)
public class PageLayoutDto {
    private long pageVersionId;
    private String fluentApiAlias;
    private String fluentApiSpecId;
    private List<PageLayoutModuleDto> modules;
    private List<PagePropertyMappingDto> propertyMappings;
    private FrontPageLoadingType loadingType;
    private Map<Integer, List<PageLayoutModuleDto>> sections;
}
