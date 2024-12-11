package com.example.bluevelvetmusicstore.enums;

public enum UserRole {
  ADMINISTRATOR("ALL"),
  SALES_MANAGER("NONE"),
  EDITOR("PRODUCTS"),
  SHIPPING_MANAGER("VIEW_PRODUCTS"),
  ASSISTANT("NONE");

  private final String role;

  UserRole(String role) {
    this.role = role;
  }

  @Override
  public String toString() {
    return role;
  }
}
