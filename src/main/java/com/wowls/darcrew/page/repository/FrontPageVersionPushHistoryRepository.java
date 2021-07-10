package com.wowls.darcrew.page.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FrontPageVersionPushHistoryRepository extends JpaRepository<FrontPageVersionPushHistory, Long> {
    List<FrontPageVersionPushHistory> findByFrontPageIdAndCreatedAtBetween(Long frontPageId, Date startedAt, Date endedAt);
    @Query("SELECT h FROM FrontPageVersionPushHistory h WHERE h.frontPageId =?1 AND h.pushResult = ?2 ORDER BY h.frontPageVersionPushHistoryId DESC")
    List<FrontPageVersionPushHistory> findFirst10ByPageId(Long frontPageId, boolean pushResult);
    FrontPageVersionPushHistory findFirstByFrontPageIdAndPushResultTrueOrderByFrontPageVersionPushHistoryIdDesc(Long id);
}
