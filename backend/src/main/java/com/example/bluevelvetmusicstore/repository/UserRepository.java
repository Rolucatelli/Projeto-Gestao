package com.example.bluevelvetmusicstore.repository;

import com.example.bluevelvetmusicstore.model.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

  @Query(
      """
    SELECT u
    FROM User u
    JOIN u.roles r
    WHERE (:roleName IS NULL OR r.name = :roleName)
""")
  Page<User> findAllByRoleName(@Param("roleName") String roleName, Pageable pageable);

  boolean existsByEmail(String email);
}
