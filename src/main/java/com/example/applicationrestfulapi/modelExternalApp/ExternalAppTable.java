package com.example.applicationrestfulapi.modelExternalApp;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="external_app")
public class ExternalAppTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "is_active")
    private Boolean isActive;
}
