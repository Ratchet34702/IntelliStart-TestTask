package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "users")
@NoArgsConstructor
public class User {
    @Id
    @Column(name = "id_user")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name")
    @NotNull(message = "First name can not be null")
    @NotBlank(message = "First name can not be blank")
    private String firstName;

    @NotNull(message = "Last name can not be null")
    @NotBlank(message = "Last name can not be blank")
    @Column(name = "last_name")
    private String lastName;

    @NotNull(message = "Account balance can not stay null")
    @Min(value = 0, message = "Account balance can't be negative")
    @Column(name = "account_balance")
    private Double accountBalance;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProductUser> productUsers;

    public User(String firstName, String lastName, Double accountBalance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountBalance = accountBalance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) && firstName.equals(user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(accountBalance, user.accountBalance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, accountBalance);
    }
}
