package com.weatherApp.weatherApp.Services;

import com.weatherApp.weatherApp.Entities.Coord;
import com.weatherApp.weatherApp.Entities.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    public User createUser(User user);
    public User changeUserName(UUID userId, String newUsername);
    public void deleteUser(UUID id);
    public User getUserById(UUID id);
    public User getUserByUsername(String username);
    public List<User> getAllUsers();

    public Coord getFavorite(UUID userId);
    public void addToFavorite(Coord coord,UUID userId);
    public void removeFromFavorite(Coord coord, UUID userId);
}
