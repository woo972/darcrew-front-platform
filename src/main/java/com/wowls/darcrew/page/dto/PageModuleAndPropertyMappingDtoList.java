package com.wowls.darcrew.page.dto;

import com.wowls.darcrew.support.constant.FrontPageLoadingType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class PageModuleAndPropertyMappingDtoList {
    @Valid
    @NotEmpty
    private List<PageModuleDto> modules;

    @Valid
    private List<PagePropertyMappingDto> propertyMappings;

    private FrontPageLoadingType loadingType = FrontPageLoadingType.EAGER;
}
