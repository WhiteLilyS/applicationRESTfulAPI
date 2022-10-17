package com.example.applicationrestfulapi.repository;

import com.example.applicationrestfulapi.entity.RoleTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleTableRepository extends JpaRepository<RoleTable,Long> {
}
