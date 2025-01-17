package com.example.users.service;

import com.example.users.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final Map<String, User> users;

    public UserService() {

        List<User> userList = List.of(
            new User("1", "Иван Иванов", "ул. Пушкина, д. 1", "+7 (999) 123-45-67", "ivan@example.com"),
            new User("2", "Петр Петров", "ул. Лермонтова, д. 2", "+7 (999) 765-43-21", "petr@example.com")
        );
        
        this.users = userList.stream()
                .collect(Collectors.toMap(User::getId, Function.identity()));
    }

    public Optional<User> getUserById(String userId) {
        return Optional.ofNullable(users.get(userId));
    }
    public List<User> getAllUsers() {
        return List.copyOf(users.values());
    }
}