package com.example.demo.services;

import com.example.demo.exceptions.NotFoundException;
import com.example.demo.model.Product;
import com.example.demo.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    public void saveProduct(Product product) {
        productRepository.saveAndFlush(product);
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product getById(Long productId) {
        return productRepository.findById(productId).orElseThrow(NotFoundException::new);
    }

    public void deleteProductById(Long productId) {
        productRepository.deleteById(productId);
    }
}
