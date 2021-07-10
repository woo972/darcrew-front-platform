package com.wowls.darcrew.page.entity;


import com.wowls.darcrew.support.AuditableEntity;
import com.wowls.darcrew.support.constant.FrontPageLoadingType;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;
import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(of = "id", callSuper = false)
@Getter
@Setter
@Builder
@Entity
@Table(name="front_page_version")
public class FrontPageVersion extends AuditableEntity {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long id;

    @Column
    private boolean activated;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private FrontPageLoadingType loadingType = FrontPageLoadingType.EAGER;

    @Column
    private boolean defaultVersion;

    @Column
    private boolean deleted;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "frontPageId", nullable = false)
    private FrontPage page;

    @OneToMany(mappedBy = "pageVersion")
    @OrderBy("ordering ASC")
    @Where(clause = "deleted = 0")
    private List<FrontPageModule> pageModules;

    @OneToMany(mappedBy = "pageVersion")
    @Where(clause = "deleted =0")
    private List<FrontPagePropertyMapping> propertyMappings;
}
