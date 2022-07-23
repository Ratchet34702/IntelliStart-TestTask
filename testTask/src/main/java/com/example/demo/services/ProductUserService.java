package com.example.demo.services;

import com.example.demo.containers.ProductContainer;
import com.example.demo.containers.UserContainer;
import com.example.demo.model.ProductUser;
import com.example.demo.model.ProductUserKey;
import com.example.demo.repo.ProductUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductUserService {

    @Autowired
    private ProductUserRepository productUserRepository;
    public List<ProductContainer> getProductsByUser(Long userId) {
        List<ProductUser> productUserList = productUserRepository.findByUserId(userId);
        List<ProductContainer> productContainerList = new ArrayList<>();
        productUserList.forEach(element -> productContainerList.add(element.toProductContainer()));
        return productContainerList;
    }

    public void saveProductUser(ProductUser productUser) {
        productUserRepository.saveAndFlush(productUser);
    }

    public List<UserContainer> getUsersByProduct(Long productId) {
        List<ProductUser> productUserList = productUserRepository.findByProductId(productId);
        List<UserContainer> userContainerList = new ArrayList<>();
        productUserList.forEach(element -> userContainerList.add(element.toUserContainer()));
        return userContainerList;
    }

    public Optional<ProductUser> findById(ProductUserKey id) {
        return productUserRepository.findById(id);
    }
}
