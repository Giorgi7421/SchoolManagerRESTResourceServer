package com.example.schoolmanagerrestresourceserver.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="schoolusers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SchoolUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column(name="keycloak_id" , unique = true)
    private String keycloakId;

    @Column(name="user_name" , unique = true)
    private String userName;

    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String gender;
    private Integer age;
    private boolean isDeleted;
}
