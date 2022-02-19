package com.sales.mapper;

import com.sales.model.Category;
import com.sales.pojo.CategoryModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CategoryMapper {

    @Mapping(target = "categoryId", source = "id")
    CategoryModel convert(Category category);
}
