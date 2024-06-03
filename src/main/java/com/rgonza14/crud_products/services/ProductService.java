package com.rgonza14.crud_products.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rgonza14.crud_products.exceptions.ResourceNotFoundException;
import com.rgonza14.crud_products.models.ProductModel;
import com.rgonza14.crud_products.repositories.ProductRepository;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Transactional
    public ProductModel saveProduct(ProductModel product) {
        return productRepository.save(product);
    }

    public List<ProductModel> getProducts() {
        return (List<ProductModel>) productRepository.findAll();
    }

    public ProductModel getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("product", "id", id));
    }

    public ProductModel updateProductById(ProductModel request, Long id) {
        ProductModel product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("producto", "id", id));

        product.setTitle(request.getTitle());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        return productRepository.save(product);

    }

    public boolean deleteProductById(Long id) {
        if (!productRepository.existsById(id)) {
            return false;
        }
        productRepository.deleteById(id);
        return true;
    }

}
