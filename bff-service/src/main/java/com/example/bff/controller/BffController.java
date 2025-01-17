package com.example.bff.controller;

import com.example.bff.model.UserData;
import com.example.bff.service.BffService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/site-bff")
@RequiredArgsConstructor
public class BffController {
    private final BffService bffService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserData> getUserData(@PathVariable String userId) {
        return bffService.getUserData(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}