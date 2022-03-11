package com.example.ecommerceweb.repository;

import com.example.ecommerceweb.model.Table;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TableRepository extends JpaRepository<Table, Long> {
    Table findTableByTableTypes(Integer tableType);
}
