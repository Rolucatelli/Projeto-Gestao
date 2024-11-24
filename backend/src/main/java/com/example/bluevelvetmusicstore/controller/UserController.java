package com.example.bluevelvetmusicstore.controller;

import com.example.bluevelvetmusicstore.model.entities.User;
import com.example.bluevelvetmusicstore.model.vo.CreateUserVO;
import com.example.bluevelvetmusicstore.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/v1")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            User createdUser = userService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}
