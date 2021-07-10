package com.wowls.darcrew.page.controller;

import com.wowls.darcrew.page.dto.RequestResult;
import com.wowls.darcrew.page.service.PageModuleQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
//@RequestMapping("/pages/{pageId}/versions/{versionId}")
@RequestMapping("/pages/{pageId}")
public class PageModuleRestController {
    private final PageModuleQueryService pageModuleQueryService;

    @GetMapping("/modules")
    public RequestResult getPageModules() {
        return new RequestResult(pageModuleQueryService.getPageModules());
    }
//    public RequestResult getPageModules(@PathVariable long versionId) {
//        return new RequestResult(pageModuleQueryService.getPageModules(versionId));
//    }
}
