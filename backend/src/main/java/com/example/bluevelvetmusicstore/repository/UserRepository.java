package com.example.bluevelvetmusicstore.repository;

import com.example.bluevelvetmusicstore.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    boolean existsByEmail(String email);
} 

