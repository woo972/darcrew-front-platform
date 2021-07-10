package com.wowls.darcrew.provider.repogitory;

import com.wowls.darcrew.provider.entity.FrontProvider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FrontProviderRepository extends JpaRepository<FrontProvider, Long> {
    Page<FrontProvider> findByNameContainingAndDeletedFalse(String name, Pageable pageable);
    FrontProvider findByName(String name);
    FrontProvider findByIdAndDeletedFalse(long id);
}
