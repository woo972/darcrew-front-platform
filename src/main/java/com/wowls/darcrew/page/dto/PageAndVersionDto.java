package com.wowls.darcrew.page.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class PageAndVersionDto {
    private Long pageId;
    private String pageName;
    private Long versionId;
    private Boolean isDefaultVersion;
}
