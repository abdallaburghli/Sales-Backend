package com.sales.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class TransactionModel {

    private UUID transactionId;

    @NotNull
    private UUID productId;

    @NotNull
    private BigDecimal price;

    @NotNull
    private Integer quantity;
}
