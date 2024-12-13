package com.example.bluevelvetmusicstore.repository;

import com.example.bluevelvetmusicstore.model.entities.Details;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailRepository extends JpaRepository<Details, Long> {
}
