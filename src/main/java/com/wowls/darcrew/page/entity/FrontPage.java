package com.wowls.darcrew.page.entity;

import com.wowls.darcrew.provider.entity.FrontProvider;
import com.wowls.darcrew.support.AuditableEntity;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(of = "id", callSuper = false)
@Getter
@Setter
@Builder
@Entity
@Table(name="front_page")
public class FrontPage extends AuditableEntity {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long id;

    @Column(unique = true, nullable = false, length = 100)
    private String name;

    @Column(length = 200)
    private String description;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "frontProviderId", nullable = false)
//    private FrontProvider provider;
//
//    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "page")
//    @OrderBy("id DESC")
//    @Where(clause = "deleted = 0")
//    private List<FrontPageVersion> versions;
//
//    @Column
//    private boolean deleted;


}
