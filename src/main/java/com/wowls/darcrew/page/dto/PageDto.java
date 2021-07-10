package com.wowls.darcrew.page.dto;

import com.wowls.darcrew.support.constant.FrontPageLoadingType;
import com.wowls.darcrew.support.validation.annotation.ValidPageName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class PageDto {

    private Long id;
    @ValidPageName
    private String name;
    @Size(max = 200)
    @NotEmpty
    private String description;
    @Valid
    private List<PageModuleDto> modules;
    //    private List<PageVersionDto> versions;
    //    private FrontPageLoadingType defaultVersionLoadingType;
    //    private List<PagePropertyMappingDto> propertyMappings;
    //    @NotNull
    //    @Min(1)
    //    private Long providerId;
    //    private String providerName;
    //    private Long defaultVersionId;
}
