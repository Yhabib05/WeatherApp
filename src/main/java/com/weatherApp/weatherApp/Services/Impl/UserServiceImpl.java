package com.weatherApp.weatherApp.Services.Impl;

import com.weatherApp.weatherApp.Entities.Coord;
import com.weatherApp.weatherApp.Entities.User;
import com.weatherApp.weatherApp.Repositories.UserRepository;
import com.weatherApp.weatherApp.Services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private void validateNewUser(User user){
        if(null!=user.getId()){
            throw new IllegalArgumentException("ID should not be provided; it is generated automatically.");
        }
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("Username mustn't be empty");
        }
        if(userRepository.findByUsername(user.getUsername())!=null){
            throw new IllegalArgumentException("Username already exists");
        }

    }
    @Override
    public User createUser(User user){
        validateNewUser(user);
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("User not found with id" + id));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Coord getFavorite(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new IllegalArgumentException("User not found with id" + userId));
        return user.getFavoriteCoord();
    }


    @Override
    public void addToFavorite(Coord coord, User user) {
        user.setFavoriteCoord(coord);
        userRepository.save(user); //never forget to persist the changes
    }

    @Override
    public void removeFromFavorite(Coord coord, User user) {
        user.setFavoriteCoord(null);
        userRepository.save(user);
    }
}
