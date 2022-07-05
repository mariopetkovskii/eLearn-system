package com.example.elearningsystem.service;

import com.example.elearningsystem.model.User;

public interface AuthService {
    User login(String username, String password);
}
