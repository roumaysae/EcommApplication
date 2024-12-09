package com.roumaysae.inventoryservice.repository;

import com.roumaysae.inventoryservice.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
