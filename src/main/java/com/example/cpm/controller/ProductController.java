package com.example.cpm.controller;

import com.example.cpm.exception.InvalidInputException;
import com.example.cpm.exception.ResourceNotFoundException;
import com.example.cpm.model.Product;
import com.example.cpm.service.ProductService;
import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/product/")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<Product> getAllProduct(){
        return productService.getAllProducts();
    }

    @GetMapping("{id}")
    public Optional<Product> getProductById(@PathVariable Long id) {
        return productService.findProductById(id);
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {

        if (StringUtils.isBlank(product.getName())){
            throw new InvalidInputException("Product name from Request Body is null!");
        }
        if (StringUtils.isBlank(product.getItemType())){
            throw new InvalidInputException("Product item type from Request Body is null!");
        }
        if (product.getPrice() == null){
            throw new InvalidInputException("Product price from Request Body is null!");
        }
        if (product.getQuantity() == null){
            throw new InvalidInputException("Product quantity from Request Body is null!");
        }
        if (product.getOwner() == null){
            throw new InvalidInputException("Product owner from Request Body is null!");
        }
        Product createdProduct = productService.saveProduct(product);

        return createdProduct;
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping
    public Product updateProduct(@RequestBody Product product) {

        Optional<Product> optionalProduct = productService.findProductById(product.getId());

        if (optionalProduct.isPresent()) {
            Product updatedProduct = productService.updateProduct(product);
            return updatedProduct;
        } else {
            throw new ResourceNotFoundException(String.format("Product with id %o not found", product.getId()));
        }
    }
}
