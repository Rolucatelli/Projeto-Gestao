package com.example.bluevelvetmusicstore.enums;

import lombok.Getter;

@Getter
public enum ProductSortField {
  id("id"),
  name("name"),
  category("category.name");

  private final String fieldName;

  ProductSortField(String fieldName) {
    this.fieldName = fieldName;
  }
}
