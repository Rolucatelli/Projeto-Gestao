package com.example.bluevelvetmusicstore.enums;

public enum UserRole {
  ADMINISTRATOR("Administrator"),
  SALES_MANAGER("Sales Manager"),
  EDITOR("Editor"),
  SHIPPING_MANAGER("Shipping Manager"),
  ASSISTANT("Assistant");

  private final String role;

  UserRole(String role) {
    this.role = role;
  }

  @Override
  public String toString() {
    return role;
  }
}
