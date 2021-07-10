package com.wowls.darcrew.page.controller;

import com.wowls.darcrew.page.dto.PageDto;
import com.wowls.darcrew.page.dto.PageModuleAndPropertyMappingDtoList;
import com.wowls.darcrew.page.dto.PageSearchCondition;
import com.wowls.darcrew.page.dto.RequestResult;
import com.wowls.darcrew.page.service.PageCommandService;
import com.wowls.darcrew.page.service.PageQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("pages")
@RequiredArgsConstructor
public class PageRestController {
    private final PageQueryService pageQueryService;
    private final PageCommandService pageCommandService;

    @GetMapping
    public RequestResult getPages(@ModelAttribute PageSearchCondition condition, Pageable pageable) {
        return new RequestResult(pageQueryService.getPageList(condition, pageable));
    }

    @GetMapping("/{pageId}")
    public RequestResult getPage(@PathVariable Long pageId) {
        return new RequestResult(pageQueryService.getPageById(pageId));
    }

    @GetMapping("/names/{name}/non-duplicate")
    public RequestResult isNotDuplicatedPageName(@PathVariable String name) {
        return new RequestResult(pageQueryService.isNotDuplicatedName(name));
    }

    @PostMapping("/register")
    public RequestResult register(@RequestBody @Valid PageDto pageDto) {
        return new RequestResult(pageCommandService.register(pageDto));
    }

    @PutMapping("/{pageId}")
    public RequestResult update(@PathVariable Long pageId,
                                @RequestBody @Valid PageDto pageDto) {
        return new RequestResult(pageCommandService.update(pageDto));
    }

    //    @PostMapping("/{pageId}/remove")
//    @Secured("PAGE_LAYOUT_MANAGER")
//    public RequestResult remove(@PathVariable Long pageId) {
//        pageCommandService.remove(pageId);
//        return RequestResult.SUCCESS;
//    }
    @DeleteMapping("/{pageId}")
    public RequestResult remove(@PathVariable Long pageId) {
        pageCommandService.remove(pageId);
        return RequestResult.SUCCESS;
    }
}
