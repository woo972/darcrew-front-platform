package com.wowls.darcrew.version.service;

import com.wowls.darcrew.version.entity.AppVersion;
import com.wowls.darcrew.version.repogitory.AppVersionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class AppVersionService {
    private static final int VERSION_LENGTH = 3;
    private final AppVersionRepository appVersionRepository;

    public AppVersion findAppVersion(String versionString) {
        List<Integer> version = getVersionElements(versionString);
        AppVersion appVersion = appVersionRepository.findByMajorAndMinorAndRevision(
                version.get(0),
                version.get(1),
                version.get(2)
        );
        if (appVersion == null) {
            return appVersionRepository.persist(createNew(version));
        }
        return appVersion;
    }

    private List<Integer> getVersionElements(String versionString) {
        String[] splitedVersions = StringUtils.split(versionString, AppVersion.VERSION_DELIMITER);
        if (Objects.isNull(splitedVersions) || splitedVersions.length != VERSION_LENGTH) {
            log.error("can not get the app version with this param : {}", versionString);
            throw new IllegalArgumentException("Wrong param format or param is null");
        }
        return Arrays.stream(splitedVersions).map(element -> Integer.parseInt(element)).collect(Collectors.toList());
    }

    private AppVersion createNew(List<Integer> version) {
        return AppVersion.builder()
                .major(version.get(0))
                .minor(version.get(1))
                .revision(version.get(2))
                .build();
    }

    public AppVersion createNew(String versionString) {
        return createNew(getVersionElements(versionString));
    }
}
