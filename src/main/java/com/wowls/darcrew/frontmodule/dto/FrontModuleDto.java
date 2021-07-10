package com.wowls.darcrew.frontmodule.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wowls.darcrew.support.constant.FrontModuleType;
import com.wowls.darcrew.support.validation.annotation.AppVersion;
import com.wowls.darcrew.support.validation.annotation.ValidModuleName;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Size;

import java.util.Date;

import static com.wowls.darcrew.support.constant.JsonDate.DATE_FORMAT_ADMIN;
import static com.wowls.darcrew.support.constant.JsonDate.TIME_ZONE_SEOUL;

@Getter
@Setter
@Accessors(chain = true)
public class FrontModuleDto {
    private static final String API_LINK_PREFIX = "http://api-forge.xxx/details?specId=";

    private Long id;
    @ValidModuleName
    private String name;
    private boolean activated;
    private boolean pageable;
    @NonNull
    @Size(max = 200)
    private String description;
    @Size(max = 100)
    private String imagePath;
    private String cdnImagePath;
    private MultipartFile imageFile;
    @Size(max = 200)
    private String apiSpecId;
    @AppVersion
    private String androidSupportedVersion;
    @AppVersion
    private String iosSupportedVersion;
    private boolean deletable;
    private FrontModuleType moduleType;
    private Long readTimeout;
    @JsonFormat(pattern = DATE_FORMAT_ADMIN, timezone = TIME_ZONE_SEOUL)
    private Date createdAt;
    @JsonFormat(pattern = DATE_FORMAT_ADMIN, timezone = TIME_ZONE_SEOUL)
    private Date modifiedAt;
    private String createdBy;
    private String modifiedBy;

}
