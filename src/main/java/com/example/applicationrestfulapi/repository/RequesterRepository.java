package com.example.applicationrestfulapi.repository;

import com.example.applicationrestfulapi.entity.RequesterTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequesterRepository extends JpaRepository<RequesterTable, Long> {
    RequesterTable findByIin(String iin);

    Boolean existsByIin(String iin);

}
