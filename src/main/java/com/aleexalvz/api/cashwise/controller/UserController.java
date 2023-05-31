package com.aleexalvz.api.cashwise.controller;

import com.aleexalvz.api.cashwise.dto.UserDto;
import com.aleexalvz.api.cashwise.model.UserModel;
import com.aleexalvz.api.cashwise.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    final UserService userService;
    final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody @Valid UserDto user) {
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(user, userModel);
        userModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        userModel.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                "User " + user.getEmail() + " created successfully."
        );
    }

    @GetMapping()
    public ResponseEntity<Object> getAllUserEmails() {
        List<UserModel> users = userService.findAll();
        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Users not found.");
        }
        List<String> userEmails = users.stream().map(UserModel::getEmail).toList();

        return ResponseEntity.status(HttpStatus.OK).body(userEmails);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable(value = "id") String id) {
        Optional<UserModel> userModelOptional = userService.findById(id);
        if (userModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(userModelOptional.get());
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteAllUsers() {
        userService.deleteAll();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Users deleted");
    }
}
