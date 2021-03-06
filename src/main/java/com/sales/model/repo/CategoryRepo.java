package com.sales.model.repo;

import com.sales.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CategoryRepo extends JpaRepository<Category, UUID> {

    Optional<Category> findById(UUID id);

    Page<Category> findAll(Pageable pageable);
}
