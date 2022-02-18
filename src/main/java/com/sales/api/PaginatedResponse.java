package com.sales.api;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PaginatedResponse<T> {

    private List<T> entries = new ArrayList<>();
    private long totalElements;
    private int totalPages;
    private int currentPage;

    public <U> PaginatedResponse(List<T> entries, Page<U> page) {
        this.entries = entries;
        this.totalElements = page.getTotalElements();
        this.totalPages = page.getTotalPages();
        this.currentPage = page.getNumber();
    }
}
