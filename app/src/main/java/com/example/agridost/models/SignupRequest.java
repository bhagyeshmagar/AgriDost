package com.example.agridost.models;

public class SignupRequest {
    private String name;
    private String phone;
    private String password;
    private String address;
    private String dob;

    public SignupRequest(String name, String phone, String password, String address, String dob) {
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.address = address;
        this.dob = dob;
    }
}
