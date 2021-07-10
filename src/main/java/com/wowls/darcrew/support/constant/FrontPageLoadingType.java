package com.wowls.darcrew.support.constant;

import java.util.Arrays;
import java.util.List;

public enum FrontPageLoadingType {
    EAGER,
    LAZY;

    public static List<FrontPageLoadingType> getLoadingTypeList() {
        return Arrays.asList(FrontPageLoadingType.values());
    }
}
