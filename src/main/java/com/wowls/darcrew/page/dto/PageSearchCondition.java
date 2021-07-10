package com.wowls.darcrew.page.dto;

import com.wowls.darcrew.page.entity.FrontPage;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

@Getter
@Setter
@Accessors(chain = true)
public class PageSearchCondition {
    private String name;
    private String providerName;
    private String sortBy;
    private Boolean sortAsc;

    public Specifications<FrontPage> getSpecifications() {
        Specifications<FrontPage> specifications = PageSpecs.PROVIDER_FETCHER;
        if (StringUtils.isNotBlank(this.name)) {
            specifications = specifications.and(PageSpecs.nameContains(this.name));
        }
        if (StringUtils.isNotBlank(this.providerName)) {
            specifications = specifications.and(PageSpecs.providerContains(this.providerName));
        }
        if (Objects.nonNull(this.sortBy)) {
            specifications = specifications.and(PageSpecs.sort(this.sortBy, this.sortAsc));
        }
        return specifications;
    }
}
