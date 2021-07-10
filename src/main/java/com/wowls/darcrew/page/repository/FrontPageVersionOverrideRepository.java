package com.wowls.darcrew.page.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FrontPageVersionOverrideRepository extends JpaRepository<FrontPageVersionOverride, Long> {
    FrontPageVersionOverride findByFrontPageIdAndDevicdeInfoAndDeviceInfoType(Long pageId,
                                                                              String deviceId,
                                                                              DeviceInfoType deviceInfoType);
    FrontPageVersionOverride findByFrontPageIdANdDeviceInfoAndDeviceInfoTypeAndEnabledTrueAndModifiedAtAfter(Long pageId,
                                                                                                             String deviceId,
                                                                                                             DeviceInfoType deviceInfoType,
                                                                                                             Date modifiedAt);
    List<FrontPageVersionOverride> findAllByFrontPageIdAndFrontPageVersionIdAndEnabledTrueAndModifedAtAfter(Long pageId, Long pageVersionId, Date modifiedAt);
}
