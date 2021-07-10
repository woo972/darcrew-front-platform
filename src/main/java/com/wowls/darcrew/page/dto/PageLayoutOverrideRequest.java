package com.wowls.darcrew.page.dto;

import com.wowls.darcrew.support.constant.ClientOsType;
import com.wowls.darcrew.support.validation.annotation.AppVersion;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Accessors(chain = true)
public class PageLayoutOverrideRequest {
    @Min(1)
    @ApiModelProperty(required = true)
    private long pageVersionId;

    @NotNull
    @ApiModelProperty(required = true)
    private ClientOsType osType;

    @AppVersion
    @ApiModelProperty(required = true)
    private String appVersion;

}
