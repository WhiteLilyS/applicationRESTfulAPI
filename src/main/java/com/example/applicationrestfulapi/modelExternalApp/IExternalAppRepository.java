package com.example.applicationrestfulapi.modelExternalApp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IExternalAppRepository extends JpaRepository<ExternalAppTable,Long> {
        ExternalAppTable findExternalAppTableByName(String name);
}
