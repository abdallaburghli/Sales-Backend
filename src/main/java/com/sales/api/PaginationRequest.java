package com.sales.api;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;

@Getter
@Setter
public class PaginationRequest {

    private int pageNumber = 0;
    private int pageLength = 10;

    public PageRequest pageRequest() {
        return PageRequest.of(pageNumber, pageLength);
    }
}
