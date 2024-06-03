package com.rgonza14.crud_products.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rgonza14.crud_products.exceptions.BadRequestException;
import com.rgonza14.crud_products.models.ProductModel;
import com.rgonza14.crud_products.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ProductModel saveProduct(@Valid @RequestBody ProductModel product) {
        try {

            return this.productService.saveProduct(product);
        } catch (DataAccessException exDt) {
            throw new BadRequestException(exDt.getMessage());
        }

    }

    @GetMapping
    public List<ProductModel> getProducts() {
        return this.productService.getProducts();
    }

    @PutMapping(path = "/{id}")
    public ProductModel updateProductById(@RequestBody ProductModel product, @Valid @PathVariable("id") Long id) {
        try {

            return this.productService.updateProductById(product, id);
        } catch (DataAccessException exDt) {
            throw new BadRequestException(exDt.getMessage());
        }
    }

    @DeleteMapping(path = "/{id}")
    public boolean deleteProductById(@PathVariable("id") Long id) {
        return this.productService.deleteProductById(id);
    }

}
