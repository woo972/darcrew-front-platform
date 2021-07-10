package com.wowls.darcrew.page.dto;

import com.wowls.darcrew.support.validation.annotation.ValidPageName;
import com.wowls.darcrew.support.constant.ClientOsType;
import com.wowls.darcrew.support.validation.annotation.AppVersion;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Accessors(chain = true)
public class PageLayoutRequest {
    @ValidPageName
    @ApiModelProperty(required = true)
    private String pageName;

    @NotNull
    @ApiModelProperty(required = true)
    private ClientOsType osType;

    @AppVersion
    @ApiModelProperty(require = true)
    private String appVersion;
}
