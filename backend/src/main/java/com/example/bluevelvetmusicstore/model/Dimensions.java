package com.example.bluevelvetmusicstore.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Dimensions {

    private BigDecimal length;
    private BigDecimal width;
    private BigDecimal height;
    private BigDecimal weight;

}
