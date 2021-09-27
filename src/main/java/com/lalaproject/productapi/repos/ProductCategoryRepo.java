package com.lalaproject.productapi.repos;

import com.lalaproject.productapi.models.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepo extends JpaRepository<ProductCategory, Long>
{

    Boolean existsByName(String name);
}

