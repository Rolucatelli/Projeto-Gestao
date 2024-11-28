package com.example.bluevelvetmusicstore.model.vo;

import java.math.BigDecimal;
import java.util.List;

public record CreateProductVO(
        String name,
        String shortDescription,
        String fullDescription,
        String category,
        String brand,
        BigDecimal length,
        BigDecimal width,
        BigDecimal height,
        BigDecimal weight,
        BigDecimal price,
        BigDecimal cost,
        Integer discount,
        Boolean active,
        int stock,
        List<CreateImagesVO> images) {}
