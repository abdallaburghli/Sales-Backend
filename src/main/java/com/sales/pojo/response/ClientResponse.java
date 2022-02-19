package com.sales.pojo.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sales.model.Client;
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
    @JsonIgnore
    private Client client;
}
