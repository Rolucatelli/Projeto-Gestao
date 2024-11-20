package com.example.bluevelvetmusicstore.repository;

import java.util.Optional;

import com.example.bluevelvetmusicstore.model.entities.Category;
import javax.swing.text.html.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
  Optional<Category> findByName(String name);
}
