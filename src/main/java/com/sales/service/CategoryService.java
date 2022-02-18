package com.sales.service;

import com.sales.api.PaginatedResponse;
import com.sales.api.PaginationRequest;
import com.sales.pojo.CategoryModel;

import java.util.UUID;

public interface CategoryService {

    CategoryModel createCategory(CategoryModel request);

    CategoryModel updateCategory(CategoryModel request, UUID categoryId);

    PaginatedResponse<CategoryModel> retrieveCategories(PaginationRequest request);
}
