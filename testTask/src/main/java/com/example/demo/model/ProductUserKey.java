package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@Data
public class ProductUserKey implements Serializable {
    @Column(name = "product_id")
    protected Long productId;

    @Column(name = "user_id")
    protected Long userId;

    public ProductUserKey(Product product, User user) {
        this.productId = product.getId();
        this.userId = user.getId();
    }

    public ProductUserKey(Long productId, Long userId) {
        this.productId = productId;
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductUserKey that = (ProductUserKey) o;
        return productId.equals(that.productId) && userId.equals(that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, userId);
    }
}
