package com.example.bluevelvetmusicstore.model.entities;

import jakarta.persistence.*;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

  @Id
  @Column(length = 128)
  private String email;

  @Column(name = "first_name", nullable = false, length = 60)
  private String firstName;

  @Column(name = "last_name", nullable = false, length = 60)
  private String lastName;

  @Column(nullable = false, length = 64)
  private String password;

  @Column(nullable = false)
  private Boolean enabled;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "user_roles",
      joinColumns = {@JoinColumn(name = "user_id")},
      inverseJoinColumns = {@JoinColumn(name = "role_id")})
  private List<Role> roles;

  public List<String> getRoleNames() {
    return roles.stream().map(Role::getName).toList();
  }
}
