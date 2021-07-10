package com.wowls.darcrew.frontmodule.service;


import com.wowls.darcrew.page.entity.FrontModule;

public class FrontModuleSpecs {
    private static final String LIKE_WILDCARD = "%";

    public static final Specifications<FrontModule> APP_VERSION_FETCHER =
            Specifications.where((root, query, cb) -> {
                if (!isCountQuery(query)) {
                    root.fetch(androidSupportedVersion);
                    root.fetch(iosSupportedVersion);
                }
                return cb.and(cb.isFalse(root.get(deleted)));
            });

}
