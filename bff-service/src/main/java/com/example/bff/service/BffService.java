package com.example.bff.service;

import com.example.bff.model.Order;
import com.example.bff.model.UserData;
import com.example.bff.model.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BffService {
    private final WebClient.Builder webClientBuilder;
    private static final String USERS_SERVICE_URL = "http://localhost:8081";
    private static final String ORDERS_SERVICE_URL = "http://localhost:8082";

    public Optional<UserData> getUserData(String userId) {
        try {
            UserProfile user = webClientBuilder.build()
                    .get()
                    .uri(USERS_SERVICE_URL + "/api/users/" + userId)
                    .retrieve()
                    .bodyToMono(UserProfile.class)
                    .block();

            List<Order> orders = webClientBuilder.build()
                    .get()
                    .uri(ORDERS_SERVICE_URL + "/api/orders/by-user/" + userId)
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<List<Order>>() {})
                    .block();

            return Optional.of(new UserData(user, orders));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}