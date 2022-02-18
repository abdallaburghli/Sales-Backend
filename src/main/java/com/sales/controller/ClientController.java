package com.sales.controller;

import com.sales.api.PaginatedResponse;
import com.sales.api.PaginationRequest;
import com.sales.api.Response;
import com.sales.pojo.request.ClientRequest;
import com.sales.pojo.response.ClientResponse;
import com.sales.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping("/api/v1/clients")
    public Response<ClientResponse> createClient(@RequestBody ClientRequest request) {
        ClientResponse res = clientService.createClient(request);
        return new Response<>(res);
    }

    @PutMapping("/api/v1/clients/{clientId}")
    public Response<ClientResponse> createClient(@PathVariable UUID clientId, @RequestBody ClientRequest request) {
        ClientResponse res = clientService.updateClient(request, clientId);
        return new Response<>(res);
    }

    @PostMapping("/api/v1/clients/all")
    public Response<PaginatedResponse<ClientResponse>> retrieveClients(@RequestBody PaginationRequest request) {
        PaginatedResponse<ClientResponse> res = clientService.retrieveClients(request);
        return new Response<>(res);
    }
}
