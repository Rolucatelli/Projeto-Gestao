package com.example.bluevelvetmusicstore.model.vo;

import java.math.BigDecimal;

public record ProductDashboardVO(
    Long id,
    String imageUrl,
    String name,
    String description,
    String brand,
    String category,
    BigDecimal price) {}
