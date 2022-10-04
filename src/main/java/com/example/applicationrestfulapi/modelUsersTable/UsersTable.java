package com.example.applicationrestfulapi.modelUsersTable;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "users")
//TODO понять можно ли привязать SOAP с Entity
//при условии что нет переработать и изменить под SOAP
public class UsersTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private Boolean is_active;
}
