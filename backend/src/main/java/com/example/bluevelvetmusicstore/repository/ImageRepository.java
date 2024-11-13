package com.example.bluevelvetmusicstore.repository;

import com.example.bluevelvetmusicstore.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}
