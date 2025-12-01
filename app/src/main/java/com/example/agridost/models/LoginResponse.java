package com.example.agridost.models;

public class LoginResponse {
    private boolean success;
    private String token;
    private User user;

    public boolean isSuccess() {
        return success;
    }

    public String getToken() {
        return token;
    }

    public User getUser() {
        return user;
    }

    public static class User {
        private String name;
        private String phone;
    }
}
