package com.sales.service;

import com.sales.api.PaginatedResponse;
import com.sales.api.PaginationRequest;
import com.sales.pojo.request.ClientRequest;
import com.sales.pojo.request.ProductRequest;
import com.sales.pojo.response.ClientResponse;
import com.sales.pojo.response.ProductResponse;

import java.util.UUID;

public interface ProductService {

    ProductResponse createProduct(ProductRequest request);

    ProductResponse updateProduct(ProductRequest request, UUID productId);

    PaginatedResponse<ProductResponse> retrieveProducts(PaginationRequest request);
}
