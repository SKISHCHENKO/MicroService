package com.example.users.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String id;
    private String fullName;
    private String deliveryAddress;
    private String phoneNumber;
    private String email;
}