package com.wowls.darcrew.version.entity;

import com.wowls.darcrew.support.AuditableEntity;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Builder
@Table(name = "app_version")
@EqualsAndHashCode(of = "id", callSuper = false)
public class AppVersion extends AuditableEntity {

    public static final String VERSION_DELIMITER = ".";

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private int major;

    @Column(nullable = false)
    private int minor;

    @Column(nullable = false)
    private int revision;

    @Transient
    public String getVersion(){
        StringBuilder versionBuilder = new StringBuilder();
        versionBuilder
                .append(major)
                .append(VERSION_DELIMITER)
                .append(minor)
                .append(VERSION_DELIMITER)
                .append(revision);
        return versionBuilder.toString();
    }

}
