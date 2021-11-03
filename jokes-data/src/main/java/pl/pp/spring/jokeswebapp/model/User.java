package pl.pp.spring.jokeswebapp.model;

import javax.persistence.*;

@Entity
@Table(name = "user")   //ustawianie nazwy tabeli
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") //ustawianie nazwy kolumny
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;
}
