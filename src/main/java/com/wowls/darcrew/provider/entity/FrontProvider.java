package com.wowls.darcrew.provider.entity;

import com.wowls.darcrew.page.entity.FrontPage;
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
@Table(name = "front_providers")
public class FrontProvider extends AuditableEntity {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "provider")
    @Where(clause = "deleted = 0")
    private List<FrontPage> pages;

    @OneToMany(mappedBy = "provider")
    @Where(clause = "deleted = 0")
    @OrderBy("apiAlias DESC")
    private List<FrontProviderFluentApi> fluentApis;

    @Column
    private boolean deleted;
}
