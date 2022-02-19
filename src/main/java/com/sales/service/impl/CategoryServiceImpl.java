package com.sales.service.impl;

import com.sales.api.PaginatedResponse;
import com.sales.api.PaginationRequest;
import com.sales.mapper.CategoryMapper;
import com.sales.model.Category;
import com.sales.model.repo.CategoryRepo;
import com.sales.pojo.CategoryModel;
import com.sales.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;
    private final CategoryMapper mapper = Mappers.getMapper(CategoryMapper.class);

    @Override
    public CategoryModel createCategory(CategoryModel request) {
        Category category = new Category();

        return populateCategory(category, request);
    }

    @Override
    public CategoryModel updateCategory(CategoryModel request, UUID categoryId) {
        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category does not exist."));

        return populateCategory(category, request);
    }

    @Override
    public PaginatedResponse<CategoryModel> retrieveCategories(PaginationRequest request) {
        Page<Category> page = categoryRepo.findAll(request.pageRequest());

        List<CategoryModel> categories = page.stream()
                .map(mapper::convert)
                .collect(toList());

        return new PaginatedResponse<>(categories, page);
    }

    @Override
    public CategoryModel retrieveCategory(UUID categoryId) {
        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        CategoryModel model = mapper.convert(category);
        model.setCategory(category);
        return model;
    }

    private CategoryModel populateCategory(Category category, CategoryModel request) {
        category.setName(request.getName());
        category.setDescription(request.getDescription());

        categoryRepo.save(category);
        return mapper.convert(category);
    }
}
