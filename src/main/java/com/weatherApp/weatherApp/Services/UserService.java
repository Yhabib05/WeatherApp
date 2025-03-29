package com.weatherApp.weatherApp.Services;

import com.weatherApp.weatherApp.Entities.Coord;
import com.weatherApp.weatherApp.Entities.User;

import java.util.UUID;

public interface UserService {
    public User createUser(User user);
    public void deleteUser(UUID id);

    public void addToFavorite(Coord coord,User user);
    public void removeFromFavorite(Coord coord);
}
