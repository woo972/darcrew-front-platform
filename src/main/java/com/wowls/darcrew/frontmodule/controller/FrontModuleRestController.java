package com.wowls.darcrew.frontmodule.controller;

import com.wowls.darcrew.frontmodule.dto.FrontModuleDto;
import com.wowls.darcrew.frontmodule.service.FrontModuleCommandService;
import com.wowls.darcrew.frontmodule.service.FrontModuleQueryService;
import com.wowls.darcrew.page.dto.RequestResult;
import com.wowls.darcrew.page.service.PageVersionQueryService;
import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/modules")
public class FrontModuleRestController {
    private final FrontModuleQueryService frontModuleQueryService;
    private final FrontModuleCommandService frontModuleCommandService;
    private final PageVersionQueryService pageVersionQueryService;

    @GetMapping
    public RequestResult getModules(@SortDefault(sort = "name") Pageable pageable) {
        return new RequestResult(frontModuleQueryService.getModuleList(pageable));
    }

    @PostMapping("/save")
    public RequestResult save(@ModelAttribute @Valid FrontModuleDto frontModuleDto,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new RequestResult(RequestResult.Status.FAIL,
                    bindingResult.getFieldError().getDefaultMessage(),
                    bindingResult.getFieldError());
        }
        frontModuleCommandService.save(frontModuleDto);
        return RequestResult.SUCCESS;
    }

    @PostMapping("/delete")
    @Secured("PAGE_LAYOUT_MANAGER")
    public RequestResult delete(@RequestParam Long moduleId) {
        frontModuleCommandService.delete(moduleId);
        return RequestResult.SUCCESS;
    }

    //    @GetMapping(params = "")
    @GetMapping("/name/{name}")
    public boolean isUniqueName(@PathVariable String name) {
        return frontModuleQueryService.isUniqueName(name);
    }

    @GetMapping("{moduleName}/info")
    public RequestResult getModuleInfo(@PathVariable String moduleName) {
        Preconditions.checkState(StringUtils.isNotBlank(moduleName));
        return new RequestResult(frontModuleQueryService.getModuleDto(moduleName));
    }

    @GetMapping("/{moduleId}/page-list")
    public RequestResult getPageList(@PathVariable long moduleId) {
        return new RequestResult(pageVersionQueryService.getPageAndVersionListByModuleId(moduleId));
    }
}
