package com.revature.service;

import com.revature.model.User;

public class AuthService {

    public void register(User user) {

        if (user.getUserName().contains(" ") || user.getPassword().contains(" ")) {
            throw new IllegalArgumentException("Spaces are not allowed");
        }
    }
}
