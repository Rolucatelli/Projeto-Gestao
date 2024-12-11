package com.example.bluevelvetmusicstore.model.entities;

import jakarta.persistence.*;
import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
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

  @Column(nullable = true, length = 80)
  private String photo;

  @Column(nullable = false)
  private Boolean enabled;

  @Column(nullable = false)
  private String username;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "user_roles",
      joinColumns = {@JoinColumn(name = "user_id")},
      inverseJoinColumns = {@JoinColumn(name = "role_id")})
  private List<Role> roles;

  public List<String> getRoleNames() {
    return roles.stream().map(Role::getName).toList();
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String getRole() {
    return roles.isEmpty() ? null : roles.get(0).getName();
  }

  public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
  }
}
