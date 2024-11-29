package com.example.bluevelvetmusicstore.controller;

import com.example.bluevelvetmusicstore.enums.UserRole;
import com.example.bluevelvetmusicstore.model.vo.UserDataVO;
import com.example.bluevelvetmusicstore.service.user.UserService;
import lombok.RequiredArgsConstructor;
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

  @GetMapping("/{id}")
  public ResponseEntity<UserDataVO> getUser(@PathVariable String id) {
    UserDataVO userDataVO = userService.findUserByEmail(id);
    return ResponseEntity.ok(userDataVO);
  }

  @GetMapping("/all")
  public ResponseEntity<Page<UserDataVO>> getAllUser(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size,
      @RequestParam(required = false) UserRole searchRole) {
    Pageable pageable =
        PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "firstName", "lastName"));
    Page<UserDataVO> userDataVO = userService.retrieveAllUsers(searchRole, pageable);
    return ResponseEntity.ok(userDataVO);
  }
}
