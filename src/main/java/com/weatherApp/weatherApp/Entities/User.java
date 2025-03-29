package com.weatherApp.weatherApp.Entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    //pour forcer que ça soit non nulle et unique
    @Column(unique = true, nullable = false)
    private String username;

    @Embedded
    private Coord favoriteCoord;

    public String getUsername() {
        return username;
    }

    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public void setFavoriteCoord(Coord favoriteCoord) {
        this.favoriteCoord = favoriteCoord;
    }

    public Coord getFavoriteCoord() {
        return favoriteCoord;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
