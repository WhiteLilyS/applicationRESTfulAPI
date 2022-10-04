package com.example.applicationrestfulapi.modelUsersTable;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersTableRepository extends JpaRepository<UsersTable,Long> {
    UsersTable findByUsername(String username);
    Boolean existsByUsername(String username);

}
