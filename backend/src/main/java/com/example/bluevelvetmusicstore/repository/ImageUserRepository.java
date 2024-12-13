package com.example.bluevelvetmusicstore.repository;

import com.example.bluevelvetmusicstore.model.entities.ImageUser;
import com.example.bluevelvetmusicstore.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ImageUserRepository extends JpaRepository<ImageUser, Long> {
    Optional<ImageUser> findByUser(User user);
}