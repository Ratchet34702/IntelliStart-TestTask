package com.example.demo.model;

import com.example.demo.containers.ProductContainer;
import com.example.demo.containers.UserContainer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "product_user")
@NoArgsConstructor
public class ProductUser {
    @EmbeddedId
    private ProductUserKey id;

    @JsonIgnore
    @ManyToOne (fetch = FetchType.LAZY)
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "amount")
    private Long amount;

    public ProductUser(Product product, User user, Long amount) {
        this.product = product;
        this.user = user;
        this.amount = amount;
        this.id = new ProductUserKey(product, user);
    }

    public ProductContainer toProductContainer() {
        return new ProductContainer(this);
    }

    public UserContainer toUserContainer() {
        return new UserContainer(this);
    }
}
