package com.wowls.darcrew.page.entity;

import com.wowls.darcrew.support.AuditableEntity;
import com.wowls.darcrew.support.constant.FrontModuleType;
import com.wowls.darcrew.version.entity.AppVersion;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Builder
@Getter
@Setter
@Entity
@Table(name = "front_module")
public class FrontModule extends AuditableEntity {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long id;

    @Column(length = 100, nullable = false, unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private FrontModuleType moduleType;

    @Column
    private boolean activated;

    @Column
    private boolean pageable;

    @Column(length = 200)
    private String description;

    @Column(length = 1000)
    private String imagePath;

    @Column(name = "apiInfoLink", length = 200)
    private String apiSpecId;

    @Column(name = "frontProviderName", length = 100)
    private String providerName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "androidSupportVersionId", nullable = false)
    private AppVersion androidSupportedVersion;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "androidSupportVersionId", nullable = false)
    private AppVersion iosSupportedVersion;

//    @OneToMany(mappedBy = "module")
//    @Where(clause = "deleted = 0")
//    private List<FrontProviderFluentApiModule> fluentApiModules;

    @OneToMany(mappedBy = "module")
    @Where(clause = "deleted = 0")
    private List<FrontPageModule> pageModules;

    @Column
    private boolean deleted;

//    @Transient
//    public boolean isDeletable(){
//        if (Objects.nonNull(fluentApiModules) && fluentApiModules.size() > 0
//                || (Objects.nonNull(pageModules) && pageModules.size() > 0)) {
//            return false;
//        }
//        return true;
//    }
}
