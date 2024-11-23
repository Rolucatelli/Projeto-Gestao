package com.example.bluevelvetmusicstore.service.user;

import com.example.bluevelvetmusicstore.model.entities.User;
import com.example.bluevelvetmusicstore.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.example.bluevelvetmusicstore.model.vo.CreateUserVO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User createUser(User user){
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        user.setEnabled(true);
        return userRepository.save(user);
    }
}

