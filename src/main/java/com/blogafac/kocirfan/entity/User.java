package com.blogafac.kocirfan.entity;

import javax.persistence.*;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name="user")
@Data
@NoArgsConstructor

public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name="user_name")
    String username;

    @Column(name="last_name")
    String lastname;

    @Column(name="password")
    String password;


}
