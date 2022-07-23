package com.example.demo.controllers;

import com.example.demo.containers.UserContainer;
import com.example.demo.exceptions.NotEnoughMoneyException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.model.User;
import com.example.demo.services.ProductUserService;
import com.example.demo.services.UserService;
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
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductUserService productUserService;

    @PostMapping("/add_user")
    public ResponseEntity<?> addUser(@Valid @RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors
                    .getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList()));
        }
        userService.saveUser(user);
        return ResponseEntity.ok("User " + user.getLastName() + " " + user.getFirstName() + " added successfully");
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @PostMapping("/buy")
    public ResponseEntity<String> buyProducts(@RequestParam(value = "idUser") Long userId,
                            @RequestParam(value = "idProduct") Long productId,
                            @RequestParam(value = "amount") Long amount) {
        try {
            userService.buyProduct(productId, userId, amount);
        } catch (NotFoundException | NotEnoughMoneyException | IllegalArgumentException exp1) {
            return ResponseEntity.badRequest().body(exp1.getMessage());
        }
        return ResponseEntity.ok("Bought successfully");

    }

    @GetMapping("/find_by_product/{idProduct}")
    public ResponseEntity<List<UserContainer>> getUsersByProduct(@PathVariable(value = "idProduct") Long productId) {
        return ResponseEntity.ok(productUserService.getUsersByProduct(productId));
    }

    @DeleteMapping("/delete_user/{idUser}")
    public ResponseEntity<String> deleteUser(@PathVariable("idUser") Long userId) {
        User user;
        try {
            user = userService.getById(userId);
        } catch (NotFoundException exp) {
            return ResponseEntity.badRequest().body(exp.getMessage());
        }
        userService.deleteUserById(userId);
        return ResponseEntity.ok("User " + user.getLastName() + " " +
                user.getFirstName() + " was deleted successfully");

    }


}
