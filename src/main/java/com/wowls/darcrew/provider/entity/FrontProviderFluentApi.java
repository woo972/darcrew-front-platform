package com.wowls.darcrew.provider.entity;

import com.wowls.darcrew.support.AuditableEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(of = "id", callSuper = false)
@Getter
@Setter
@Entity
@Table(name = "front_provider_fluent_apis")
public class FrontProviderFluentApi extends AuditableEntity {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long id;

    @Column(unique = true, nullable = false)
    private String apiAlias;

    @Column
    private boolean defaultApi;

    @Column(length = 100)
    private String specId;

    @Column(length = 200)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "frontProviderId", nullable = false)
    private FrontProvider provider;

    @OneToMany(mappedBy = "fluentApi")
    @Where(clause = "deleted = 0")
    private List<FrontProviderFluentApiModule> modules;

    @Column
    private boolean deleted;
}
