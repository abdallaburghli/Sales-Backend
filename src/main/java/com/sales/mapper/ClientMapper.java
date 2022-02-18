package com.sales.mapper;

import com.sales.model.Client;
import com.sales.pojo.request.ClientRequest;
import com.sales.pojo.response.ClientResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ClientMapper {

    Client convert(ClientRequest request);

    @Mapping(target = "clientId", source = "id")
    ClientResponse convert(Client client);
}
