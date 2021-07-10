package com.wowls.darcrew.page.repository;

import com.wowls.darcrew.page.entity.FrontPage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FrontPageRespository extends JpaRepository<FrontPage, Long> { 
    FrontPage findByName(String name);
    FrontPage findByIdAndDeletedFalse(long id);
    List<FrontPage> findByProviderIdAndDeletedFalse(long providerId);
}
