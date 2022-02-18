package com.sales.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Getter
@Setter
public class CategoryModel {

    private UUID categoryId;

    @NotEmpty
    private String name;

    private String description;
}
