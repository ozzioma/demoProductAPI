package com.lalaproject.productapi.controllers;


import com.lalaproject.productapi.models.ProductCategory;
import com.lalaproject.productapi.repos.ProductCategoryRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/product/category")
public class ProductCategoryController {

    private final ProductCategoryRepo productCategoryRepo;

    ProductCategoryController(ProductCategoryRepo repository) {
        productCategoryRepo = repository;
    }

    @GetMapping
    public List<ProductCategory> findAll() {
        return productCategoryRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductCategory> findById(@PathVariable(value = "id") long id) {
        Optional<ProductCategory> single = productCategoryRepo.findById(id);

        if (single.isPresent()) {
            return ResponseEntity.ok().body(single.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/addNew")
    public ResponseEntity<ProductCategory> addEmployee(@Validated @RequestBody ProductCategory data) {
        var newEntity = productCategoryRepo.save(data);
        return new ResponseEntity<>(newEntity, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductCategory> updateEmployee(@PathVariable Long id, @Validated @RequestBody ProductCategory data) {
        Boolean checkExists = productCategoryRepo.existsById(id);

        if (checkExists) {
            var updatedEntity = productCategoryRepo.save(data);
            return ResponseEntity.ok().body(updatedEntity);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        Boolean checkExists = productCategoryRepo.existsById(id);

        if (checkExists) {
            productCategoryRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
