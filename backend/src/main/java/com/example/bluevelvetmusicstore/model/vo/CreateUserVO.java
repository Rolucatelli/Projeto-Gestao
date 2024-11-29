package com.example.bluevelvetmusicstore.model.vo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserVO(
    @Email String email,
    @NotBlank @Size(min = 2, max = 60) String firstName,
    @NotBlank @Size(min = 2, max = 60) String lastName,
    @NotBlank String password,
    String photo) {}
