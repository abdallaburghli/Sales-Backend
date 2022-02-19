package com.sales.model.repo;

import com.sales.model.Category;
import com.sales.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepo extends JpaRepository<Product, UUID> {

    Optional<Product> findById(UUID id);

    Page<Product> findAll(Pageable pageable);

    List<Product> findAllByIdIn(List<UUID> ids);
}
