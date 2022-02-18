package com.sales.service.impl;

import com.sales.mapper.ClientMapper;
import com.sales.model.Client;
import com.sales.model.repo.ClientRepo;
import com.sales.pojo.request.ClientRequest;
import com.sales.pojo.response.ClientResponse;
import com.sales.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
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

        Client client = mapper.convert(request);

        clientRepo.save(client);
        return mapper.convert(client);
    }

    @Override
    public ClientResponse updateClient(ClientRequest request, UUID clientId) {
        Client client = clientRepo.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client does not exist"));

        if (!client.getEmail().equals(request.getEmail()) && clientRepo.existsByEmail(request.getEmail()))
            throw new RuntimeException("Client with the same email already exists");

        client = mapper.convert(request);
        clientRepo.save(client);

        return mapper.convert(client);
    }

    @Override
    public List<ClientResponse> retrieveClients() {
        List<Client> clients = clientRepo.findAll();
        return clients.stream()
                .map(mapper::convert)
                .collect(toList());
    }
}
