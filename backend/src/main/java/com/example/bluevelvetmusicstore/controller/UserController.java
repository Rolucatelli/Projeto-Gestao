package com.example.bluevelvetmusicstore.controller;

import com.example.bluevelvetmusicstore.model.entities.User;
import com.example.bluevelvetmusicstore.model.vo.CreateUserVO;
import com.example.bluevelvetmusicstore.model.vo.ExceptionVO;
import com.example.bluevelvetmusicstore.model.vo.UserDataVO;
import com.example.bluevelvetmusicstore.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/v1")
public class UserController {

  private final UserService userService;

  @PostMapping("/create")
  public ResponseEntity<?> createUser(@RequestBody @Valid CreateUserVO createUserVO) {
    try {
      User createdUser = userService.createUser(createUserVO);
      return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    } catch (IllegalArgumentException ex) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionVO(ex.getMessage()));
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDataVO> getUser(@PathVariable String id) {
    UserDataVO userDataVO = userService.findUserByEmail(id);
    return ResponseEntity.ok(userDataVO);
  }

  @GetMapping("/all")
  public ResponseEntity<Page<UserDataVO>> getAllUser(
      @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
    Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "firstName", "lastName"));
    Page<UserDataVO> userDataVO = userService.retrieveAllUsers(pageable);
    return ResponseEntity.ok(userDataVO);
  }

  @DeleteMapping("/delete/{email}")
  public ResponseEntity<String> deleteUser(@PathVariable String email) {
    try {
      userService.deleteUser(email);
      return ResponseEntity.ok("User deleted with sucess.");
    } catch (IllegalArgumentException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found: " + e.getMessage());
    }
  }

  @PutMapping("/update/{email}")
  public ResponseEntity<?> updateUser(
      @PathVariable String email,
      @RequestBody CreateUserVO updatedUserVO) {
    try {
      UserDataVO updatedUser = userService.updateUser(email, updatedUserVO);
      return ResponseEntity.ok(updatedUser);
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body("User not found");
    }
  }
}
