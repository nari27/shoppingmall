package com.mall.model;

import lombok.Data;

@Data
public class User {
    private int userId;
    private String username;
    private String password;
    private String email;
    private String phone_number;
    private String role;
    private String address;
}
