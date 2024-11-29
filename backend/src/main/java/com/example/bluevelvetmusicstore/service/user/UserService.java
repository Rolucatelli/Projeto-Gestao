package com.example.bluevelvetmusicstore.service.user;
import com.example.bluevelvetmusicstore.model.entities.User;
import com.example.bluevelvetmusicstore.model.vo.CreateUserVO;

public interface UserService {
    User createUser(CreateUserVO createUser);
}
