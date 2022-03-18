package com.example.ecommerceweb.repository;

import com.example.ecommerceweb.model.AdminDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface AdminRepository extends JpaRepository<AdminDetail, Long> {
    Optional<AdminDetail> findByEmail(String email);
}
