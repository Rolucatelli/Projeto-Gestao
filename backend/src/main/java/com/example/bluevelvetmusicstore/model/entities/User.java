package com.example.bluevelvetmusicstore.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@Data
public class User {

  @Id
  @Column(nullable = false, unique = true, length = 128)
  private String email;

  @Column(name = "first_name", nullable = false, length = 60)
  private String firstName;

  @Column(name = "last_name", nullable = false, length = 60)
  private String lastName;

  @Column(nullable = false, length = 60)
  private String password;

  @Column(nullable = true, length = 80)
  private String photo;

  @Column(nullable = false)
  private Boolean enabled;
}
