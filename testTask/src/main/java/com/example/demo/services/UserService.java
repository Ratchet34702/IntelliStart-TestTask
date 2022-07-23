package com.example.demo.services;

import com.example.demo.exceptions.NotEnoughMoneyException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.model.Product;
import com.example.demo.model.ProductUser;
import com.example.demo.model.ProductUserKey;
import com.example.demo.model.User;
import com.example.demo.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductUserService productUserService;

    public void saveUser(User user) {
        userRepository.saveAndFlush(user);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getById(Long userId) {
        return userRepository.findById(userId).orElseThrow(NotFoundException::new);
    }

    public void buyProduct(Long productId, Long userId, Long amount) {
        Product product = productService.getById(productId);
        User user = this.getById(userId);
        if (amount < 1) {
            throw new IllegalArgumentException("Amount must be bigger than 0");
        }
        if (user.getAccountBalance() < product.getPrice() * amount) {
            throw new NotEnoughMoneyException(user.getAccountBalance(), product.getPrice() * amount);
        }
        user.setAccountBalance(user.getAccountBalance() - product.getPrice() * amount);
        this.saveUser(user);
        ProductUser productUser;
        Optional<ProductUser> optionalProductUser = productUserService.findById(new ProductUserKey(productId, userId));
        if (optionalProductUser.isEmpty()) {
            productUser = new ProductUser(productService.getById(productId), getById(userId), amount);
        } else {
            productUser = optionalProductUser.get();
            productUser.setAmount(productUser.getAmount() + amount);
        }
        productUserService.saveProductUser(productUser);
    }

    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }
}
