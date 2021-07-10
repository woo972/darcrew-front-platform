package com.wowls.darcrew.page.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class PageDtoList {
    private List<PageDto> pages;
    private long totalElements;
    private int totalPages;
}
