package com.example.demo.containers;

import com.example.demo.model.Product;
import com.example.demo.model.ProductUser;
public class ProductContainer {

    private Product product;

    private Long amount;

    public ProductContainer(ProductUser productUser) {
        this.product = productUser.getProduct();
        this.amount = productUser.getAmount();
    }

    public Product getProduct() {
        return product;
    }

    public Long getAmount() {
        return amount;
    }
}
