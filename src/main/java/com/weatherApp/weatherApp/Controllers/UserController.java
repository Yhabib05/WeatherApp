package com.weatherApp.weatherApp.Controllers;

import com.weatherApp.weatherApp.Entities.Coord;
import com.weatherApp.weatherApp.Entities.User;
import com.weatherApp.weatherApp.Services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user){
        return ResponseEntity.ok(userService.createUser(user));
    }
    @DeleteMapping
    public ResponseEntity<?> deleteUser(@RequestParam UUID id){
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    @GetMapping
    public ResponseEntity<?> getAllUser(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("favorite")
    public ResponseEntity<?> addToFavorite(@RequestBody Coord coord, @RequestParam UUID userId){
        try{
            User user= userService.getUserById(userId);
            userService.addToFavorite(coord, user);
            return ResponseEntity.ok("City added to favorite");
        }catch(IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("favorite")
    public ResponseEntity<?> removeFromFavorite(@RequestBody Coord coord, @RequestParam UUID userId){
        try {
            User user = userService.getUserById(userId);
            userService.removeFromFavorite(coord, user);
            return ResponseEntity.ok("City removed from favorite");
        } catch(IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("favorite")
    public ResponseEntity<?> getFavorite(@RequestParam UUID userId){
        try {
            User user = userService.getUserById(userId);
            return ResponseEntity.ok(userService.getFavorite(userId));
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
