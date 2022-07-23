package com.example.demo.exceptions;

public class NotEnoughMoneyException extends RuntimeException {
    public NotEnoughMoneyException(Double balance, Double required) {
        super("Not enough money for purchase." +
                "\nYour balance: " + balance +
                "\nRequired: " + required);
    }

    public NotEnoughMoneyException() {
        super("Not enough money for purchase.");
    }

}
