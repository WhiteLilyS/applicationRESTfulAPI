package com.example.applicationrestfulapi.modelUsersTable;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "users")
public class UsersTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private Boolean is_active;
}
