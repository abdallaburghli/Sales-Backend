package com.sales.pojo.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ClientResponse {

    private UUID clientId;
    private String email;
    private String name;
    private String lastName;
    private String mobile;
}
