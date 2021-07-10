package com.wowls.darcrew.provider.entity;

import com.wowls.darcrew.page.entity.FrontModule;
import com.wowls.darcrew.support.AuditableEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@EqualsAndHashCode(of = "id", callSuper = false)
@Getter
@Setter
@Entity
@Table(name = "front_provider_fluent_api_modules")
public class FrontProviderFluentApiModule extends AuditableEntity {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "frontProviderFluentApiId")
    private FrontProviderFluentApi fluentApi;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "frontModuleId")
    private FrontModule module;

    @Column
    private boolean deleted;
}
