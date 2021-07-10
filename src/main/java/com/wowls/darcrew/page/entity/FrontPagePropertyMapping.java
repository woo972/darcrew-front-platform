package com.wowls.darcrew.page.entity;

import com.wowls.darcrew.support.AuditableEntity;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@EqualsAndHashCode(of = "id", callSuper = false)
@Builder
@Getter
@Setter
@Entity
@Table(name="front_page_property_mapping")
public class FrontPagePropertyMapping extends AuditableEntity {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "frontPageVersionId", nullable = false)
    private FrontPageVersion pageVersion;

    @ManyToOne(fetch =FetchType.LAZY, optional = false)
    @JoinColumn(name = "sourceModuleId", nullable = false)
    private FrontModule sourceModule;

    @Column(length = 100, nullable = false)
    private String sourcePropertyName;

    @Column(length = 100, nullable = false)
    private String targetPropertyName;

    @Column
    private boolean deleted;
}
