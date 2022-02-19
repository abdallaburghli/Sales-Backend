package com.sales.service;

import com.sales.api.PaginatedResponse;
import com.sales.api.PaginationRequest;
import com.sales.pojo.SaleModel;


import java.util.UUID;

public interface SaleService {

    SaleModel createSale(SaleModel request);

    SaleModel updateSale(SaleModel request, UUID saleId);

    PaginatedResponse<SaleModel> retrieveSales(PaginationRequest request);
}
