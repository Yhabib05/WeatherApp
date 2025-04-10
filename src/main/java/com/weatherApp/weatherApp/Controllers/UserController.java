package com.weatherApp.weatherApp.Controllers;

import com.weatherApp.weatherApp.Dto.UpdateUsernameRequest;
import com.weatherApp.weatherApp.Entities.Coord;
import com.weatherApp.weatherApp.Entities.User;
import com.weatherApp.weatherApp.Services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /* --------------- Users controllers ----------------- */
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable UUID userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody @Valid User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
    }

    @GetMapping("/search/{username}")
    public ResponseEntity<User> searchUserByUsername(@PathVariable String username){
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable UUID userId ,@RequestBody UpdateUsernameRequest request){
        return ResponseEntity.ok(userService.changeUserName(userId,request.getNewUsername() ));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID userId){
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    /* --------------- Favorite controllers ----------------- */
    @PostMapping("/{userId}/favorite")
    public ResponseEntity<String> addToFavorite(@RequestBody Coord coord, @PathVariable UUID userId){
        userService.addToFavorite(coord, userId);
        return ResponseEntity.ok("City added to favorite");
    }

    @DeleteMapping("/{userId}/favorite")
    public ResponseEntity<?> removeFromFavorite(@RequestBody Coord coord, @PathVariable UUID userId){
        userService.removeFromFavorite(coord, userId);
        return ResponseEntity.ok("City removed from favorite");
    }

    @GetMapping("/{userId}/favorite")
    public ResponseEntity<?> getFavorite(@PathVariable UUID userId){
        return ResponseEntity.ok(userService.getFavorite(userId));
    }
}
