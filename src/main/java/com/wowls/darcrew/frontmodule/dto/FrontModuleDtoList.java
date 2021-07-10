package com.wowls.darcrew.frontmodule.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class FrontModuleDtoList {
    private List<FrontModuleDto> modules;
    private long totalElements;
    private int totalPages;

    public static final FrontModuleDtoList emptyModuleList = new FrontModuleDtoList();
}
