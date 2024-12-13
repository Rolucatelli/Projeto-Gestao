package com.example.bluevelvetmusicstore.model.entities;

import jakarta.annotation.sql.DataSourceDefinition;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "imageuser")
public class ImageUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "photo", nullable = false)
    private byte[] photo;

    private String name = "";

    @Column(nullable = false)
    private Boolean isPrincipal = false;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;
}