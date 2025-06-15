package com.example.ejemplothymeleaf.repository;

import com.example.ejemplothymeleaf.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
