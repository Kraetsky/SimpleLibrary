package com.example.test.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String password;
    private String name;
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "is_active")
    private Boolean isActive;

}
