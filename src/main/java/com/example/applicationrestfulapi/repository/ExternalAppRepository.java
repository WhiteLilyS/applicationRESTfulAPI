package com.example.applicationrestfulapi.repository;

import com.example.applicationrestfulapi.entity.ExternalAppTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExternalAppRepository extends JpaRepository<ExternalAppTable, Long> {
    ExternalAppTable findExternalAppTableByName(String name);
}
