package com.example.bluevelvetmusicstore.model.vo;

import com.example.bluevelvetmusicstore.enums.UserRole;

public record UserDataVO(String email, String firstName, String lastName, Boolean enabled, String userRole, PhotoVO photo) {
}
