package com.sales.controller;

import com.sales.api.PaginatedResponse;
import com.sales.api.PaginationRequest;
import com.sales.api.Response;
import com.sales.pojo.SaleModel;
import com.sales.pojo.response.ProductResponse;
import com.sales.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class SaleController {

    private final SaleService saleService;


    @PostMapping("/api/v1/sales")
    public Response<SaleModel> createSale(@RequestBody SaleModel request) {
        SaleModel res = saleService.createSale(request);
        return new Response<>(res);
    }

    @PutMapping("/api/v1/sales/{saleId}")
    public Response<SaleModel> updateSale(@PathVariable UUID saleId, @RequestBody SaleModel request) {
        SaleModel res = saleService.updateSale(request, saleId);
        return new Response<>(res);
    }

    @PostMapping("/api/v1/sales/all")
    public Response<PaginatedResponse<SaleModel>> retrieveSakes(@RequestBody PaginationRequest request) {
        PaginatedResponse<SaleModel> res = saleService.retrieveSales(request);
        return new Response<>(res);
    }
}
