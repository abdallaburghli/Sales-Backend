package com.sales.pojo.response;

import com.sales.pojo.CategoryModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class ProductResponse {

    private UUID productId;
    private String name;
    private String description;
    private CategoryModel category;
    private LocalDateTime createdDate;
}
