package com.wowls.darcrew.page.repository;

import com.wowls.darcrew.page.entity.FrontPageVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FrontPageVersionRespository extends JpaRepository<FrontPageVersion, Long> {
    List<FrontPageVersion> findByPageIdAndDeletedFalse(long pageId);
    FrontPageVersion findOneByPageNameAndActivatedTrueAndDefaultVErsionTrueAndDeletedFalse(String pageName);
    @Query("SELECT DISTINCT v FROM FrontPageVersion v LEFT JOIN v.pageModules m WHERE m.module.id = ?1 AND v.deleted = false ORDER BY v.page.id DESC, v.id DESC")
    List<FrontPageVersion> findbyModuleId(long moduleId);
}
