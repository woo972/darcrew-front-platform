package com.wowls.darcrew.page.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wowls.darcrew.support.constant.FrontPageLoadingType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.util.Date;
import static com.wowls.darcrew.support.constant.JsonDate.DATE_FORMAT_DEFAULT;
import static com.wowls.darcrew.support.constant.JsonDate.TIME_ZONE_SEOUL;

@Getter
@Setter
@Accessors(chain = true)
public class PageVersionDto {
    private Long id;
    private boolean activated;
    private boolean defaultVersion;
    @JsonFormat(pattern = DATE_FORMAT_DEFAULT, timezone = TIME_ZONE_SEOUL)
    private Date createdAt;
    private String createdBy;
    @JsonFormat(pattern = DATE_FORMAT_DEFAULT, timezone = TIME_ZONE_SEOUL)
    private Date modfiedAt;
    private FrontPageLoadingType loadingType;

}
