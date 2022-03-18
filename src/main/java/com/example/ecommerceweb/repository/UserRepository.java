package com.example.ecommerceweb.repository;

import com.example.ecommerceweb.model.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<UserDetail,Long> {
    Optional<UserDetail> findByEmail(String email);
}
