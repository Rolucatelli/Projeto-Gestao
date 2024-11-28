package com.example.bluevelvetmusicstore.model.vo;

import com.example.bluevelvetmusicstore.model.entities.Dimensions;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
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

    public ProductDetailsVO(Long id, String name) {
    }
}