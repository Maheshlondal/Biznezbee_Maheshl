package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
@Validated
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/save")
    public ResponseEntity<String> addUser(@RequestBody @Valid User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return new ResponseEntity<>("Email already exists", HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByMobile(user.getMobile())) {
            return new ResponseEntity<>("Mobile number already exists", HttpStatus.BAD_REQUEST);
        }

        User savedUser = userRepository.save(user);
        String response = "User added successfully with ID: " + savedUser.getId();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody @Valid User user) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        User existingUser = optionalUser.get();

        if (!existingUser.getEmail().equals(user.getEmail()) && userRepository.existsByEmail(user.getEmail())) {
            return new ResponseEntity<>("Email already exists", HttpStatus.BAD_REQUEST);
        }

        if (!existingUser.getMobile().equals(user.getMobile()) && userRepository.existsByMobile(user.getMobile())) {
            return new ResponseEntity<>("Mobile number already exists", HttpStatus.BAD_REQUEST);
        }

        existingUser.setName(user.getName());
        existingUser.setDateOfBirth(user.getDateOfBirth());
        existingUser.setEmail(user.getEmail());
        existingUser.setMobile(user.getMobile());
        existingUser.setUserType(user.getUserType());

        User updatedUser = userRepository.save(existingUser);
        String response = "User with ID " + updatedUser.getId() + " updated successfully";
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers(
            @RequestParam(required = false) String userType,
            @RequestParam(required = false) Integer ageGrater,
            @RequestParam(required = false) Integer ageLessthan
    ) {
        List<User> userList;

        if (userType != null && ageGrater != null && ageLessthan != null) {
            userList = userRepository.findByUserTypeAndAgeBetween(userType, ageGrater, ageLessthan);
        } else if (userType != null) {
            userList = userRepository.findByUserType(userType);
        } else if (ageGrater != null && ageLessthan != null) {
            userList = userRepository.findByAgeBetween(ageGrater, ageLessthan);
        } else if (ageGrater != null) {
            userList = userRepository.findByAgeGreaterThanEqual(ageGrater);
        } else if (ageLessthan != null) {
            userList = userRepository.findByAgeLessThanEqual(ageLessthan); // Modified method name
        } else {
            userList = userRepository.findAll();
        }

        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        if (!userRepository.existsById(id)) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        userRepository.deleteById(id);
        String response = "User with ID " + id + " deleted successfully";
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
