package com.example.enviro365.controller;

public class NotFoundException extends RuntimeException {

    public NotFoundException(Long id) {
        super("Could not find item with id: " + id + "\n");
    }
}