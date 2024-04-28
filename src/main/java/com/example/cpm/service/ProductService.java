package com.example.cpm.service;

import com.example.cpm.exception.ResourceNotFoundException;
import com.example.cpm.model.Product;
import com.example.cpm.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
@Log
public class ProductService {
    
    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        log.info("Getting all products data from database");
        return productRepository.findAll();
    }

    public Product saveProduct(Product product){
        log.info("Saving product data to database");
        return productRepository.save(product);
    }

    public Optional<Product> findProductById(Long id){
        log.info(String.format("Getting product data with id: %o", id));
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) throw new ResourceNotFoundException(String.format("Product with id %o is not found", id));
        return product;
    }

    public void deleteProduct(Long id) {
        log.info(String.format("Deleting product data with id: %o", id));
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) throw new ResourceNotFoundException(String.format("Product with id %o is not found", id));
        productRepository.deleteById(id);
    }

    public Product updateProduct(Product product){
        log.info(String.format("Updating product data with id: %o", product.getId()));
        return productRepository.save(product);
    }
}
