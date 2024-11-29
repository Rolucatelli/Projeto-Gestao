package com.example.bluevelvetmusicstore.model.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

  @Id
  @Column(nullable = false, unique = true, length = 128)
  private String email;

  @Column(nullable = false)
  private String firstName;

  @Column(nullable = false)
  private String lastName;

  @Column(nullable = false, length = 60)
  private String password;

  @Column(nullable = true, length = 80)
  private String photo;

  @Column(nullable = false)
  private boolean enabled;
}
