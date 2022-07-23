package com.example.demo.exceptions;

import java.util.function.Supplier;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super("Object not found: invalid user or product id");
    }
}
