package com.sales.mapper;

import com.sales.model.Sale;
import com.sales.model.Transaction;
import com.sales.pojo.SaleModel;
import com.sales.pojo.TransactionModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface SaleMapper {

    @Mapping(target = "saleId", source = "id")
    @Mapping(target = "clientId", source = "client.id")
    SaleModel convert(Sale sale);

    @Mapping(target = "transactionId", source = "id")
    @Mapping(target = "productId", source = "product.id")
    TransactionModel convert(Transaction transaction);
}
