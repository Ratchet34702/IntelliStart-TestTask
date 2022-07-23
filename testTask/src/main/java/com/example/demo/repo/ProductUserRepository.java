package com.example.demo.repo;

import com.example.demo.model.ProductUser;
import com.example.demo.model.ProductUserKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductUserRepository extends JpaRepository<ProductUser, ProductUserKey> {

    List<ProductUser> findByUserId(Long userId);

    List<ProductUser> findByProductId(Long productId);

    Optional<ProductUser> findById(ProductUserKey productUserKey);
}
