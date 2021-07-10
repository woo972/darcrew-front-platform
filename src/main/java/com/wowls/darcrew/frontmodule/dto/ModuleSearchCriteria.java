package com.wowls.darcrew.frontmodule.dto;

import com.wowls.darcrew.page.entity.FrontModule;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

@Getter
@Setter
@Accessors(chain = true)
public class ModuleSearchCriteria {
    private String name;
    private String specId;
//    private Long providerId;
//    private String providerName;
    private Boolean activated;
    private Boolean pageable;
//    private Long fluentApiId;


    // TODO: query DSL 로 대체 가능한지 확인
    public Specifications<FrontModule> getSpecification() {
        Specifications<FrontModule> specifications = APP_VERSION_FETCHER;
        if (StringUtils.isNotEmpty(this.name)) {
            specifications = specifications.and(nameLike(this.name));
        }
        if (StringUtils.isNotEmpty(this.specId)) {
            specifications = specifications.and(specIdEquals(this.specId));
        }
//        if (StringUtils.isNotEmpty(this.name)) {
//            specifications = specifications.and(providerNameEquals(this.providerName));
//        }
        if (StringUtils.isNotEmpty(this.name)) {
            specifications = specifications.and(activatedEquals(this.activated));
        }
        if (StringUtils.isNotEmpty(this.name)) {
            specifications = specifications.and(pageableEquals(this.pageable));
        }
//        if (StringUtils.isNotEmpty(this.name)) {
//            specifications = specifications.and(fluentApiIdEquals(this.fluentApiId));
//        }
        return specifications;
    }
}
