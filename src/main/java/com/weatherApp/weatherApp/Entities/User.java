package com.weatherApp.weatherApp.Entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Embedded
    private Coord favoriteCoord;

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
