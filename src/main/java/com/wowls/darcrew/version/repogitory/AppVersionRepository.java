package com.wowls.darcrew.version.repogitory;

import com.wowls.darcrew.version.entity.AppVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppVersionRepository extends JpaRepository<AppVersion, Long> {
    AppVersion findByMajorAndMinorAndRevision(int major, int minor, int revision);
}
