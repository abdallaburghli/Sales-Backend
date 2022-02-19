package com.sales.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sales.model.Category;
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

    @JsonIgnore
    private Category category;
}
