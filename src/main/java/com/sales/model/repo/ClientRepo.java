package com.sales.model.repo;

import com.sales.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClientRepo extends JpaRepository<Client, UUID> {

    boolean existsByEmail(String email);

    Optional<Client> findById(UUID id);

    Page<Client> findAll(Pageable pageable);
}
