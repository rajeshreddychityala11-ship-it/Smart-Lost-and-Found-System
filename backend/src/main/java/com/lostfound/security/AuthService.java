package com.lostfound.security;

import com.lostfound.model.User;
import com.lostfound.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository users;
    private final TokenService tokens;
    private final BCryptPasswordEncoder enc = new BCryptPasswordEncoder();

    public AuthService(UserRepository users, TokenService tokens) {
        this.users = users;
        this.tokens = tokens;
    }

    public User register(String name, String email, String password) {
        String cleanName = name == null ? "" : name.trim();
        String cleanEmail = email == null ? "" : email.trim().toLowerCase();

        if (cleanName.isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }
        if (cleanEmail.isBlank()) {
            throw new IllegalArgumentException("Email is required");
        }
        if (password == null || password.length() < 6) {
            throw new IllegalArgumentException("Password must contain at least 6 characters");
        }
        if (users.existsByEmailIgnoreCase(cleanEmail)) {
            throw new IllegalArgumentException("Email already registered. Please use another email or login.");
        }

        User u = new User();
        u.setName(cleanName);
        u.setEmail(cleanEmail);
        u.setPassword(enc.encode(password));
        try {
            return users.saveAndFlush(u);
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalArgumentException("Email is already registered or the user data conflicts with the database.");
        }
    }

    public String login(String email, String password) {
        String cleanEmail = email == null ? "" : email.trim();
        User u = users.findByEmailIgnoreCase(cleanEmail)
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        if (password == null || !enc.matches(password, u.getPassword())) {
            throw new IllegalArgumentException("Invalid email or password");
        }
        return tokens.issue(u);
    }
}
