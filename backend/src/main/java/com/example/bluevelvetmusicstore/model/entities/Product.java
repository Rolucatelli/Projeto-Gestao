package com.example.bluevelvetmusicstore.model.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String name;

  @Column(nullable = false)
  private String shortDescription;

  @Column(nullable = false)
  private String fullDescription;

  @ManyToOne
  @JoinColumn(name = "category_id", nullable = false)
  private Category category;

  @Column(nullable = false)
  private String brand;

  @Column(nullable = false)
  private BigDecimal price;

  @Column(nullable = false)
  private Integer discount;

  @Column(nullable = false)
  private Boolean isActive;

  @Column(nullable = false)
  private Integer stockAmount;

  @Setter(AccessLevel.NONE)
  @Column(nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @Setter(AccessLevel.NONE)
  private LocalDateTime updatedAt;

  @Column(nullable = false)
  private BigDecimal cost;

  @Embedded private Dimensions dimensions;

  @OneToMany(mappedBy = "product")
  List<Image> images = new ArrayList<>();

  public Product(
      String name,
      String shortDescription,
      String fullDescription,
      Category category,
      BigDecimal price,
      Integer discount,
      Boolean isActive,
      Integer stockAmount,
      BigDecimal cost,
      Dimensions dimensions) {
    this.name = name;
    this.shortDescription = shortDescription;
    this.fullDescription = fullDescription;
    this.category = category;
    this.price = price;
    this.discount = discount;
    this.isActive = isActive;
    this.stockAmount = stockAmount;
    this.cost = cost;
    this.dimensions = dimensions;
  }

  @PrePersist
  protected void onCreate() {
    this.createdAt = LocalDateTime.now();
    this.updatedAt = this.createdAt;
  }

  @PreUpdate
  protected void onUpdate() {
    this.updatedAt = LocalDateTime.now();
  }
}
