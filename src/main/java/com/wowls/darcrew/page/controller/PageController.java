package com.wowls.darcrew.page.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
* this is controller for web page
*/
@Controller
@RequestMapping("/page")
public class PageController {
    @GetMapping("/list")
    ModelAndView list() {
        ModelAndView mnv = new ModelAndView("/page/list");
        return mnv;
    }

    @GetMapping("/register")
    ModelAndView register() {
        ModelAndView mnv = new ModelAndView("/page/detail");
        mnv.addObject("title", "Create a new page");
        return mnv;
    }

    @GetMapping("/edit")
    ModelAndView edit(@RequestParam("pageId") Long pageId) {
        ModelAndView mnv = new ModelAndView("/page/detail");
        mnv.addObject("pageId", pageId);
        mnv.addObject("title", "Edit page");
        return mnv;
    }
}
