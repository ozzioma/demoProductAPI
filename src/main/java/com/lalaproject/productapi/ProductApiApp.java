package com.lalaproject.productapi;

import com.github.javafaker.Faker;
import com.lalaproject.productapi.models.Product;
import com.lalaproject.productapi.models.ProductCategory;
import com.lalaproject.productapi.repos.ProductCategoryRepo;
import com.lalaproject.productapi.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class ProductApiApp implements CommandLineRunner {


    @Autowired
    private ProductCategoryRepo productCategoryRepo;

    @Autowired
    private ProductRepo productRepo;


    public static void main(String[] args) {
        SpringApplication.run(ProductApiApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        SeedProducts();

    }

    @Transactional
    void SeedProducts() {
        Faker faker = new Faker();

        if (productCategoryRepo.count() > 0)
            return;

        for (int count = 0; count <= 5; count++) {
            String categoryName = faker.commerce().material();
            var checkCatName = productCategoryRepo.existsByName(categoryName);
            if (!checkCatName) {

                ProductCategory category = new ProductCategory();
                category.setName(categoryName);
                category.setDescription(faker.lorem().paragraph(1));

                productCategoryRepo.save(category);

                for (int count2 = 0; count2 <= 5; count2++) {
                    String productName = faker.commerce().productName();
                    Boolean checkProdName = productRepo.existsByName((productName));

                    if (!checkProdName) {
                        Product product = new Product();
                        product.setCategoryId(category.getId());
                        product.setName(productName);
                        product.setCode(faker.code().isbn10());
                        product.setSalesPrice((double) faker.number().numberBetween(10, 55));
                        product.setPurchasePrice((double) faker.number().numberBetween(5, 35));
                        product.setDescription(faker.lorem().paragraph(1));

                        productRepo.save(product);
                    }

                }

            }

        }

    }

}
