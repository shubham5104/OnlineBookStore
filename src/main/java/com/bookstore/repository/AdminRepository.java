package com.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bookstore.entity.Admins;

@Repository
public interface AdminRepository extends JpaRepository<Admins, Long> {
    boolean existsByEmail(String email);
    Admins findByEmail(String email);
}
