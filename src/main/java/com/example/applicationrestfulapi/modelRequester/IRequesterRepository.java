package com.example.applicationrestfulapi.modelRequester;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public
interface IRequesterRepository extends JpaRepository<RequesterTable, Long> {
    RequesterTable findByIin(String iin);
    Boolean existsByIin(String iin);

}
