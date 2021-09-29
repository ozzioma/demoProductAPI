package com.lalaproject.productapi.controllers;

import com.lalaproject.productapi.models.Product;
import com.lalaproject.productapi.repos.ProductRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product/")
public class ProductController {

    private final ProductRepo ProductRepo;

    ProductController(ProductRepo repository) {
        ProductRepo = repository;
    }

    @GetMapping
    public List<Product> findAll() {
        return ProductRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable(value = "id") long id) {
        Optional<Product> single = ProductRepo.findById(id);

        if (single.isPresent()) {
            return ResponseEntity.ok().body(single.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/addNew")
    public ResponseEntity<Product> addEmployee(@Validated @RequestBody Product data) {
        var newEntity = ProductRepo.save(data);
        return new ResponseEntity<>(newEntity, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateEmployee(@PathVariable Long id, @Validated @RequestBody Product data) {
        Boolean checkExists = ProductRepo.existsById(id);

        if (checkExists) {
            var updatedEntity = ProductRepo.save(data);
            return ResponseEntity.ok().body(updatedEntity);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        Boolean checkExists = ProductRepo.existsById(id);

        if (checkExists) {
            ProductRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
