package com.lgh.wordskill2026ex.entity;

public class User {

    private Long num;

    private String email;

    private String password;

    private String name;

    public User() {

    }

    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public Long getNum() {
        return num;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }
}
