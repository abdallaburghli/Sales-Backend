package com.sales.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class SaleModel {

    private UUID saleId;

    @NotNull
    private UUID clientId;

    @NotEmpty
    private String seller;

    private BigDecimal total;

    private LocalDateTime createdDate;

    @NotEmpty
    @Valid
    private List<TransactionModel> transactions = new ArrayList<>();
}
