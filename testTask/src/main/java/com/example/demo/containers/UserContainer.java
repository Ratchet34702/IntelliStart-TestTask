package com.example.demo.containers;


import com.example.demo.model.ProductUser;
import com.example.demo.model.User;

public class UserContainer {

    private User user;

    private Long amount;

    public UserContainer(ProductUser productUser) {
        this.user = productUser.getUser();
        this.amount = productUser.getAmount();
    }

    public User getUser() {
        return user;
    }

    public Long getAmount() {
        return amount;
    }
}
