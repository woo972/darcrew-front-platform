package com.wowls.darcrew.page.dto;

import com.wowls.darcrew.support.validation.annotation.ValidModuleName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Accessors(chain = true)
public class PagePropertyMappingDto {
    @ValidModuleName
    private String sourceModuleName;

    @NotNull
    @Size(max = 100)
    private String sourcePropertyName;

    @NotNull
    @Size(max = 100)
    private String targetPropertyName;
}
