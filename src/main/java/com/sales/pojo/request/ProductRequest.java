package com.sales.pojo.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GeneratorType;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
public class ProductRequest {

    @NotEmpty
    private String name;

    @NotEmpty
    private String description;

    @NotNull
    private UUID CategoryId;
}
