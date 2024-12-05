package com.example.bluevelvetmusicstore.service.user;

import com.example.bluevelvetmusicstore.enums.UserRole;
import com.example.bluevelvetmusicstore.model.entities.User;
import com.example.bluevelvetmusicstore.model.vo.CreateUserVO;
import com.example.bluevelvetmusicstore.model.vo.UserDataVO;
import com.example.bluevelvetmusicstore.repository.UserRepository;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public User createUser(CreateUserVO createUser) {

    if (userRepository.existsByEmail(createUser.email())) {
      throw new IllegalArgumentException("There is another user with the e-mail provided.");
    }

    String encryptedPassword = passwordEncoder.encode(createUser.password());

    User user =
        User.builder()
            .email(createUser.email())
            .firstName(createUser.firstName())
            .lastName(createUser.lastName())
            .password(encryptedPassword)
            .photo(createUser.photo())
            .enabled(true)
            .build();

    return userRepository.save(user);
  }

  @Override
  public UserDataVO findUserByEmail(String email) {
    User user =
        userRepository.findById(email).orElseThrow(() -> new RuntimeException("User not found"));
    return new UserDataVO(
        user.getEmail(), user.getFirstName(), user.getLastName(), user.getEnabled());
  }

  @Override
  public Page<UserDataVO> retrieveAllUsers(UserRole searchRole, Pageable pageable) {
    String roleName = (Objects.isNull(searchRole)) ? null : searchRole.toString();
    Page<User> usersPage = userRepository.findAllByRoleName(roleName, pageable);
    return usersPage.map(
        users ->
            new UserDataVO(
                users.getEmail(), users.getFirstName(), users.getLastName(), users.getEnabled()));
  }
}
