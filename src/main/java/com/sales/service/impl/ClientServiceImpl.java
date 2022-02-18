package com.sales.service.impl;

import com.sales.api.PaginatedResponse;
import com.sales.api.PaginationRequest;
import com.sales.mapper.ClientMapper;
import com.sales.model.Client;
import com.sales.model.repo.ClientRepo;
import com.sales.pojo.request.ClientRequest;
import com.sales.pojo.response.ClientResponse;
import com.sales.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepo clientRepo;
    private final ClientMapper mapper = Mappers.getMapper(ClientMapper.class);

    @Override
    public ClientResponse createClient(ClientRequest request) {
        String email = request.getEmail();
        if (clientRepo.existsByEmail(email))
            throw new RuntimeException("Client with the same email already exists");

        Client client = new Client();

        return populateClient(client, request);
    }

    @Override
    public ClientResponse updateClient(ClientRequest request, UUID clientId) {
        Client client = clientRepo.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client does not exist"));

        if (!client.getEmail().equals(request.getEmail()) && clientRepo.existsByEmail(request.getEmail()))
            throw new RuntimeException("Client with the same email already exists");

        return populateClient(client, request);
    }

    @Override
    public PaginatedResponse<ClientResponse> retrieveClients(PaginationRequest request) {
        Page<Client> page = clientRepo.findAll(request.pageRequest());
        List<ClientResponse> clients = page.stream()
                .map(mapper::convert)
                .collect(toList());
        return new PaginatedResponse<>(clients, page);
    }

    private ClientResponse populateClient(Client client, ClientRequest request) {
        client.setEmail(request.getEmail());
        client.setName(request.getName());
        client.setLastName(request.getLastName());
        client.setMobile(request.getMobile());

        clientRepo.save(client);

        return mapper.convert(client);
    }
}
