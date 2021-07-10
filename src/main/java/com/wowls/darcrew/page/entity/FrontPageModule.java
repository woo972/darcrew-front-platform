package com.wowls.darcrew.page.entity;


import com.wowls.darcrew.support.AuditableEntity;
import com.wowls.darcrew.support.constant.ClientOsType;
import com.wowls.darcrew.version.entity.AppVersion;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name="front_page_module")
@Getter
@Setter
@Builder
@EqualsAndHashCode(of = "id", callSuper = false)
public class FrontPageModule extends AuditableEntity {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private Integer ordering;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ClientOsType osType;

    @Column(nullable = false)
    private Integer sectionIndex = 0;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "frontPageVersionId", nullable = false)
//    private FrontPageVersion pageVersion;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "frontModuleId", nullable = false)
    private FrontModule module;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "startAppVersionId", nullable = false)
//    private AppVersion startVersion;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "endAppVersionId", nullable = false)
//    private AppVersion endVersion;
//
//    @Column
//    private boolean deleted;
//
//    @Transient
//    public boolean supports(AppVersion clientVersion) {
//         return AppVersionComparator.isBetween(clientVersion, startVersion, endVersion);
//    }
}
