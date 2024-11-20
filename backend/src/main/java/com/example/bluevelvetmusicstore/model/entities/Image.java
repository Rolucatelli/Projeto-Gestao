package com.example.bluevelvetmusicstore.model.entities;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String url;

    private String description = "";

    @Column(nullable = false)
    private Boolean isPrincipal = false;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}
