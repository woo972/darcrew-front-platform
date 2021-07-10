package com.wowls.darcrew.page.dto;

import com.wowls.darcrew.support.validation.annotation.AppVersion;
import com.wowls.darcrew.support.validation.annotation.ValidModuleName;
import com.wowls.darcrew.support.constant.ClientOsType;
import com.wowls.darcrew.support.constant.FrontModuleType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Accessors(chain = true)
public class PageModuleDto {
    private static final String API_LINK_PREFIX = "http://wowls.darcrew.com/details?specId=";
    private Long id;
    private int ordering;
//    private boolean pageable;
    private boolean activated;
    private int sectionIndex = 0;
    @NotNull
    private FrontModuleType moduleType;
//    @NotNull
//    private String apiSpecId;
    @NotNull
    ClientOsType osType;
    @ValidModuleName
    private String moduleName;
    @AppVersion
    private String startAppVersion;
    @AppVersion(optional = true)
    private String endAppVersion;

    public String getApiInfoLink(){
        return API_LINK_PREFIX + apiSpecId;
    }
}
