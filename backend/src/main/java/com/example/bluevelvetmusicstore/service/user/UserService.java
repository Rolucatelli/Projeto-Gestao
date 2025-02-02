package com.example.bluevelvetmusicstore.service.user;

import com.example.bluevelvetmusicstore.enums.UserRole;
import com.example.bluevelvetmusicstore.model.vo.*;
import com.example.bluevelvetmusicstore.model.entities.User;
import com.example.bluevelvetmusicstore.model.vo.CreateUserVO;
import com.example.bluevelvetmusicstore.model.vo.UserDataVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
  User createUser(CreateUserVO createUser);
  UserDataVO findUserByEmail(String email);
  void deleteUser(String email);
  UserDataVO updateUser(String email, CreateUserVO updatedUserVO);
  Page<UserDataVO> retrieveAllUsers(UserRole searchRole, Pageable pageable);
}
