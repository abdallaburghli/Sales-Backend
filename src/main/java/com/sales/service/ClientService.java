package com.sales.service;

import com.sales.pojo.request.ClientRequest;
import com.sales.pojo.response.ClientResponse;

import java.util.List;
import java.util.UUID;

public interface ClientService {

    ClientResponse createClient(ClientRequest request);

    ClientResponse updateClient(ClientRequest request, UUID clientId);

    List<ClientResponse> retrieveClients();
}
