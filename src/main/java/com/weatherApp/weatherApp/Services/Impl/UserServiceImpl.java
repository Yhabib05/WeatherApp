package com.weatherApp.weatherApp.Services.Impl;

import com.weatherApp.weatherApp.Entities.Coord;
import com.weatherApp.weatherApp.Entities.User;
import com.weatherApp.weatherApp.Repositories.UserRepository;
import com.weatherApp.weatherApp.Services.UserService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user){
        if(null!=user.getId()){
            throw new IllegalArgumentException("You shouldn't specify an id for the user, it s generated automatically");
        }
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("Username shouldn't be empty");
        }
        if(userRepository.findByUsername(user.getUsername())!=null){
            throw new IllegalArgumentException("User already exists");
        }

        return userRepository.save(user);
    }

    @Override
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }


    @Override
    public void addToFavorite(Coord coord, User user) {
        user.setFavoriteCoord(coord);
    }

    @Override
    public void removeFromFavorite(Coord coord) {

    }
}
