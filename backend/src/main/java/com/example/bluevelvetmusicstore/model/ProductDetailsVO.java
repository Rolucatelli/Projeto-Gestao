package com.example.bluevelvetmusicstore.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
public class ProductDetailsVO {
    private Long id;
    private String name;
    private String shortDescription;
    private String fullDescription;
    private String category;
    private String brand;
    private BigDecimal price;
    private Integer discount;
    private Boolean isActive;
    private Integer stockAmount;
    private BigDecimal cost;
    private Dimensions dimensions;
    private List<ImageDetailsVO> images;
}
