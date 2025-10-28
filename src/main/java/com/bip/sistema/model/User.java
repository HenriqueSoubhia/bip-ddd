package com.bip.sistema.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private Long badgeCode;

    // Construtores
    public User() {}

    public User(String name, Long badgeCode) {
        this.name = name;
        this.badgeCode = badgeCode;
    }

    public User(String name) {
        this(name, null);
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getBadgeCode() {
        return badgeCode;
    }

    public void setBadgeCode(Long badgeCode) {
        this.badgeCode = badgeCode;
    }

    public void displayInfo() {
        System.out.printf(
                "ID: %d, Name: %s, Badge: %s%n",
                id,
                name,
                (badgeCode != null ? badgeCode : "N/A")
        );
    }


}
