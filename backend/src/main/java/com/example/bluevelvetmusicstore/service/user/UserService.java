package com.example.bluevelvetmusicstore.service.user;

import com.example.bluevelvetmusicstore.model.vo.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService    {
    UserDataVO findUserByEmail(String email);

    Page<UserDataVO> retrieveAllUsers(Pageable pageable);
}
