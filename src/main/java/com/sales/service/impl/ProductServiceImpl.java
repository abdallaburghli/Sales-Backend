package com.sales.service.impl;

import com.sales.api.PaginatedResponse;
import com.sales.api.PaginationRequest;
import com.sales.mapper.CategoryMapper;
import com.sales.mapper.ProductMapper;
import com.sales.model.Product;
import com.sales.model.repo.ProductRepo;
import com.sales.pojo.CategoryModel;
import com.sales.pojo.request.ProductRequest;
import com.sales.pojo.response.ProductResponse;
import com.sales.service.CategoryService;
import com.sales.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;
import static org.springframework.data.util.Pair.toMap;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final CategoryService categoryService;
    private final ProductRepo productRepo;
    private final ProductMapper mapper = Mappers.getMapper(ProductMapper.class);
    private final CategoryMapper categoryMapper = Mappers.getMapper(CategoryMapper.class);

    @Override
    public ProductResponse createProduct(ProductRequest request) {
        Product product = new Product();

        return populateProduct(product, request);
    }

    @Override
    public ProductResponse updateProduct(ProductRequest request, UUID productId) {
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product does not exist."));

        return populateProduct(product, request);
    }

    @Override
    public PaginatedResponse<ProductResponse> retrieveProducts(PaginationRequest request) {
        Page<Product> page = productRepo.findAll(request.pageRequest());

        List<ProductResponse> products = page.stream()
                .map(this::mapToProductResponse)
                .collect(toList());

        return new PaginatedResponse<>(products, page);
    }

    @Override
    public Map<UUID, ProductResponse> retrieveProducts(List<UUID> ids) {
        List<Product> products = productRepo.findAllByIdIn(ids);

        Map<UUID, ProductResponse> productsMap = new HashMap<>();
        for (Product product : products) {
            ProductResponse productResponse = mapper.convert(product);
            productResponse.setProduct(product);
            productsMap.put(productResponse.getProductId(), productResponse);
        }
        return productsMap;
    }

    private ProductResponse populateProduct(Product product, ProductRequest request) {
        product.setName(request.getName());
        product.setDescription(request.getDescription());

        CategoryModel categoryModel = categoryService.retrieveCategory(request.getCategoryId());
        product.setCategory(categoryModel.getCategory());

        productRepo.save(product);

        return mapToProductResponse(product);
    }

    private ProductResponse mapToProductResponse(Product product) {
        ProductResponse productResponse = mapper.convert(product);
        productResponse.setCategory(categoryMapper.convert(product.getCategory()));
        return productResponse;
    }
}
