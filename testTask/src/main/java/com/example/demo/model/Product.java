package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@Table(name = "product")
@NoArgsConstructor
public class Product {
    @Id
    @Column(name = "id_product")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Product name can not be null")
    @NotBlank(message = "Product name can not be blank")
    @Column(name = "name")
    private String productName;

    @NotNull(message = "Product price can not be null")
    @Min(value = 0, message = "Product price can not be negative")
    @Column(name = "price")
    private Double price;

    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProductUser> productUsers;

    public Product(String productName, Double price) {
        this.productName = productName;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id.equals(product.id) && productName.equals(product.productName) && price.equals(product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productName, price);
    }
}
