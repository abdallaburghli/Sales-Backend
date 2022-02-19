package com.sales.model.repo;

import com.sales.model.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SaleRepo extends JpaRepository<Sale, UUID> {

    Optional<Sale> findById(UUID saleId);

    @EntityGraph(attributePaths = "transactions")
    Page<Sale> findAll(Pageable pageable);
}
