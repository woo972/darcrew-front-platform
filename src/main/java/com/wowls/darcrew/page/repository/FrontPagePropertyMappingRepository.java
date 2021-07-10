package com.wowls.darcrew.page.repository;

import com.wowls.darcrew.page.entity.FrontPagePropertyMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FrontPagePropertyMappingRepository extends JpaRepository<FrontPagePropertyMapping, Long> {
    List<FrontPagePropertyMapping> findByPageVersionIdAndDeletedFalse(long versionId);
}
