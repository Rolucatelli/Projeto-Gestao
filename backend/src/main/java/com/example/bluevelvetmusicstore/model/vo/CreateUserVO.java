package com.example.bluevelvetmusicstore.model.vo;

public record CreateUserVO(String email, String firstName, String lastName, String password, String photo, boolean enabled){}