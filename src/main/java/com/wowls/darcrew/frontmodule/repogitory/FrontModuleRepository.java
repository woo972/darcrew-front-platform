package com.wowls.darcrew.frontmodule.repogitory;

import com.wowls.darcrew.page.entity.FrontModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FrontModuleRepository extends JpaRepository<FrontModule, Long> {
    FrontModule findByNameAndDeletedFalse(String name);
    FrontModule findByNameAndDeletedTrue(String name);
    List<FrontModule> findByIdIn(List<Long> ids);
}
