package com.example.bluevelvetmusicstore.service.user;

import com.example.bluevelvetmusicstore.model.entities.User;
import com.example.bluevelvetmusicstore.model.vo.UserDataVO;
import com.example.bluevelvetmusicstore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDataVO findUserByEmail(String email) {
        User user = userRepository.findById(email).orElseThrow(()->new RuntimeException("User not found"));
        return new UserDataVO(user.getEmail(), user.getFirstName(), user.getLastName(), user.getEnabled());
    }

    @Override
    public Page<UserDataVO> retrieveAllUsers(Pageable pageable) {
        Page<User> usersPage = userRepository.findAll(pageable);
        return usersPage.map(users->new UserDataVO(users.getEmail(), users.getFirstName(), users.getLastName(), users.getEnabled()));
    }
}
