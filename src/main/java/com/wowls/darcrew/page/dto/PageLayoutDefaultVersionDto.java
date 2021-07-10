package com.wowls.darcrew.page.dto;

import com.wowls.darcrew.support.constant.FrontPageLoadingType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Getter
@Setter
@Accessors(chain = true)
public class PageLayoutDefaultVersionDto implements Serializable {
    private long pageVersionId;
    private FrontPageLoadingType loadingType;
}
