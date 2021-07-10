package com.wowls.darcrew.page.dto;

import com.wowls.darcrew.support.constant.FrontModuleType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class PageLayoutModuleDto {
    private long id;
    private String moduleName;
    private String apiSpecId;
    private FrontModuleType moduleType;
    private boolean pageable;
    private int ordering;
}
