package com.wowls.darcrew.page.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import static com.wowls.darcrew.support.constant.JsonDate.DATE_FORMAT_ADMIN;
import static com.wowls.darcrew.support.constant.JsonDate.TIME_ZONE_SEOUL;

@Getter
@Setter
@Accessors(chain = true)
public class FluentApiDto implements Serializable {
    private Long id;
    private String apiAlias;
    private boolean defaultApi;
    @Size(max=100)
    private String specId;
    @Size(max=200)
    private String description;
    @NotEmpty
    private List<Long> moduleIds;
    private String createdBy;
    private String modifedBy;
    @JsonFormat(pattern = DATE_FORMAT_ADMIN, timezone = TIME_ZONE_SEOUL)
    private Date createdAt;
    @JsonFormat(pattern = DATE_FORMAT_ADMIN, timezone = TIME_ZONE_SEOUL)
    private Date modifiedAt;

}
