package com.example.applicationrestfulapi.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="requester")
public class RequesterTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String iin;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String patronymic;

}