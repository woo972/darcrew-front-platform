package com.wowls.darcrew.page.repository;

import com.wowls.darcrew.page.entity.FrontPageModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FrontPageModuleRepository extends JpaRepository<FrontPageModule, Long> {
}
