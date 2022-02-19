package com.sales.mapper;

import com.sales.model.Product;
import com.sales.pojo.response.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ProductMapper {

    @Mapping(target = "category", ignore = true)
    @Mapping(target = "productId", source = "id")
    ProductResponse convert(Product product);
}
