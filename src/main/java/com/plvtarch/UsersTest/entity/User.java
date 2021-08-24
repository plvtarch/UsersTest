package com.plvtarch.UsersTest.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "users")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String login;
    private String password;
    private String email;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate birthdate;

    public User(String name, String login, String password, String email, LocalDate birthdate) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
        this.birthdate = birthdate;
    }

    public User() {}
}
