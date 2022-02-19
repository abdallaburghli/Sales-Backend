package com.sales.controller;

import com.sales.api.PaginatedResponse;
import com.sales.api.PaginationRequest;
import com.sales.api.Response;
import com.sales.pojo.request.ProductRequest;
import com.sales.pojo.response.ProductResponse;
import com.sales.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/api/v1/products")
    public Response<ProductResponse> createProduct(@RequestBody ProductRequest request) {
        ProductResponse res = productService.createProduct(request);
        return new Response<>(res);
    }

    @PutMapping("/api/v1/products/{productId}")
    public Response<ProductResponse> updateProduct(@PathVariable UUID productId, @RequestBody ProductRequest request) {
        ProductResponse res = productService.updateProduct(request, productId);
        return new Response<>(res);
    }

    @PostMapping("/api/v1/products/all")
    public Response<PaginatedResponse<ProductResponse>> retrieveProducts(@RequestBody PaginationRequest request) {
        PaginatedResponse<ProductResponse> res = productService.retrieveProducts(request);
        return new Response<>(res);
    }
}
