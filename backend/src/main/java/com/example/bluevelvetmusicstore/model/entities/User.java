package com.example.bluevelvetmusicstore.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
import java.util.List;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")

public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Email
    @Column(nullable = false, unique = true, length = 128)
    private String email;
  
    @Size(min = 2, max = 60)
    @Column(nullable = false)
    private String firstName;
    
    @Size(min = 2, max = 60)
    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, length = 60)
    private String password;
    
    @Column(nullable = true, length = 80)
    private String photo;

    @Column(nullable = false)
    private boolean enabled;

}
