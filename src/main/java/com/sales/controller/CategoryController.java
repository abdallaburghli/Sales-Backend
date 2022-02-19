package com.sales.controller;

import com.sales.api.PaginatedResponse;
import com.sales.api.PaginationRequest;
import com.sales.api.Response;
import com.sales.mapper.CategoryMapper;
import com.sales.pojo.CategoryModel;
import com.sales.pojo.request.ClientRequest;
import com.sales.pojo.response.ClientResponse;
import com.sales.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/api/v1/categories")
    public Response<CategoryModel> createCategory(@RequestBody CategoryModel request) {
        CategoryModel res = categoryService.createCategory(request);
        return new Response<>(res);
    }

    @PutMapping("/api/v1/categories/{categoryId}")
    public Response<CategoryModel> updateCategory(@PathVariable UUID categoryId, @RequestBody CategoryModel request) {
        CategoryModel res = categoryService.updateCategory(request, categoryId);
        return new Response<>(res);
    }

    @PostMapping("/api/v1/categories/all")
    public Response<PaginatedResponse<CategoryModel>> retrieveCategories(@RequestBody PaginationRequest request) {
        PaginatedResponse<CategoryModel> res = categoryService.retrieveCategories(request);
        return new Response<>(res);
    }
}
