package com.esg.compliance.domain.service;

import com.esg.compliance.domain.model.User;
import com.esg.compliance.domain.repository.UserRepository;
import com.esg.compliance.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User register(String username, String password) {
        if(userRepository.findByUsername(username).isPresent()) {
            throw new BusinessException("Username already exists");
        }

        String encoded =  passwordEncoder.encode(password);
        User user = User.create(username, encoded);

        return userRepository.save(user);
    }
}