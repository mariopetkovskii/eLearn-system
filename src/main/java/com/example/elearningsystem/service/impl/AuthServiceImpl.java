package com.example.elearningsystem.service.impl;

import com.example.elearningsystem.model.User;
import com.example.elearningsystem.model.exception.InvalidArgumentsException;
import com.example.elearningsystem.model.exception.InvalidUserCredentialsException;
import com.example.elearningsystem.repository.UserRepository;
import com.example.elearningsystem.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if (username==null || username.isEmpty() || password==null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        return userRepository.findByUsernameAndPassword(username,
                password).orElseThrow(InvalidUserCredentialsException::new);
    }

}
