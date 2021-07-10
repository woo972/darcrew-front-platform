package com.wowls.darcrew.frontmodule.controller;

import com.wowls.darcrew.frontmodule.dto.ModuleSearchCriteria;
import com.wowls.darcrew.frontmodule.service.FrontModuleQueryService;
import com.wowls.darcrew.page.dto.RequestResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.data.domain.Sort;

@RequiredArgsConstructor
@Controller
@RequestMapping("/module-library")
public class FrontModuleController {
    private final FrontModuleQueryService frontModuleQueryService;

    @GetMapping
    public String moduleListPage() {
        return "/frontmodule/list";
    }

    @GetMapping("/search")
    @ResponseBody
    public RequestResult getModules(
            @ModelAttribute ModuleSearchCriteria moduleSearchCriteria,
            @SortDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return new RequestResult(frontModuleQueryService.getModuleSearchList(pageable, moduleSearchCriteria));
    }

}
