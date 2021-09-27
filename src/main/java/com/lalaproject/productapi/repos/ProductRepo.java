package com.lalaproject.productapi.repos;

import com.lalaproject.productapi.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long>
{
    Boolean existsByCode(String code);
    Boolean existsByName(String name);
}
