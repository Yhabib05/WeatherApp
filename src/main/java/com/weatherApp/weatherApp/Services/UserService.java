package com.weatherApp.weatherApp.Services;

import com.weatherApp.weatherApp.Entities.Coord;
import com.weatherApp.weatherApp.Entities.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    public User createUser(User user);
    public void deleteUser(UUID id);
    public User getUserById(UUID id);
    public List<User> getAllUsers();

    public Coord getFavorite(UUID userId);
    public void addToFavorite(Coord coord,User user);
    public void removeFromFavorite(Coord coord, User user);
}
