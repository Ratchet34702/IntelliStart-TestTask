package com.example.demo.controllers;

import com.example.demo.containers.ProductContainer;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.services.ProductService;
import com.example.demo.services.ProductUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/task")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductUserService productUserService;

    @PostMapping("/add_product")
    public ResponseEntity<?> addProduct(@Valid @RequestBody Product product, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors
                    .getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList()));
        }
        productService.saveProduct(product);
        return ResponseEntity.ok("Product " + product.getProductName() + " added successfully");
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }

    @GetMapping("/find_by_user/{idUser}")
    public ResponseEntity<List<ProductContainer>> getProductsByUser(@PathVariable(value = "idUser") Long userId) {
        return ResponseEntity.ok(productUserService.getProductsByUser(userId));
    }

    @DeleteMapping("/delete_product/{idProduct}")
    public ResponseEntity<String> deleteProduct(@PathVariable("idProduct") Long productId) {
        Product product;
        try {
            product = productService.getById(productId);
        } catch (NotFoundException exp) {
            return ResponseEntity.badRequest().body(exp.getMessage());
        }
        productService.deleteProductById(productId);
        return ResponseEntity.ok("Product " + product.getProductName() + " was deleted successfully");
    }



}
