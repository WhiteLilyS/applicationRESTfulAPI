package com.example.applicationrestfulapi.modelRole;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "roles")
public class RoleTable {

    @Id
    private Long id;
    @Column(name = "role_name")
    private String roleName;

}
