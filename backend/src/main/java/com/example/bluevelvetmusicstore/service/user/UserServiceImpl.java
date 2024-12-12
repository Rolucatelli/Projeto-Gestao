package com.example.bluevelvetmusicstore.service.user;

import com.example.bluevelvetmusicstore.enums.UserRole;
import com.example.bluevelvetmusicstore.model.entities.ImageUser;
import com.example.bluevelvetmusicstore.model.entities.User;
import com.example.bluevelvetmusicstore.model.entities.Role;
import com.example.bluevelvetmusicstore.model.vo.CreateUserVO;
import com.example.bluevelvetmusicstore.model.vo.PhotoVO;
import com.example.bluevelvetmusicstore.model.vo.RoleVO;
import com.example.bluevelvetmusicstore.model.vo.UserDataVO;
import com.example.bluevelvetmusicstore.repository.ImageUserRepository;
import com.example.bluevelvetmusicstore.repository.RoleRepository;
import com.example.bluevelvetmusicstore.repository.UserRepository;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final ImageUserRepository imageUserRepository;
  private final RoleRepository roleRepository;

  @Override
  public User createUser(CreateUserVO createUser) {
    if (userRepository.existsByEmail(createUser.email())) {
        throw new IllegalArgumentException("There is another user with the e-mail provided.");
    }
    String encryptedPassword = passwordEncoder.encode(createUser.password());
    List<Role> roles = createUser.roles().stream()
            .map((RoleVO roleVO) -> roleRepository.findById(roleVO.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Role not found: " + roleVO.getId())))
            .toList();
    User user = User.builder()
            .email(createUser.email())
            .firstName(createUser.firstName())
            .lastName(createUser.lastName())
            .password(encryptedPassword)
            .enabled(true)
            .roles(roles)
            .build();
    User savedUser = userRepository.save(user);
    if (createUser.photo() != null) {
        ImageUser imageUser = ImageUser.builder()
                .photo(createUser.photo().photo())
                .name(createUser.photo().name())
                .isPrincipal(true)
                .user(savedUser) 
                .build();
        imageUserRepository.save(imageUser);
        savedUser.setImagesUser(List.of(imageUser));
    }
    return savedUser;
}

  @Override
  public UserDataVO findUserByEmail(String email) {
    User user = userRepository.findById(email).orElseThrow(() -> new RuntimeException("User not found"));
    return new UserDataVO(
        user.getEmail(), user.getFirstName(), user.getLastName(), user.getEnabled(), user.getRoles().get(0).getName(), new PhotoVO(imageUserRepository.findByUser(user).orElse(new ImageUser()).getPhoto(), imageUserRepository.findByUser(user).orElse(new ImageUser()).getName()));
  }

  @Override
  public Page<UserDataVO> retrieveAllUsers(UserRole searchRole, Pageable pageable) {
    String roleName = (Objects.isNull(searchRole)) ? null : searchRole.toString();
    Page<User> usersPage = userRepository.findAllByRoleName(roleName, pageable);
    return usersPage.map(
        users -> new UserDataVO(
            users.getEmail(), users.getFirstName(), users.getLastName(), users.getEnabled(), users.getRoles().get(0).getName(), new PhotoVO(imageUserRepository.findByUser(users).orElse(new ImageUser()).getPhoto(), imageUserRepository.findByUser(users).orElse(new ImageUser()).getName())));
  }

  @Override
  public void deleteUser(String email) {
    if (!userRepository.existsById(email)) {
      throw new IllegalArgumentException("User with the provided email does not exist.");
    }
    userRepository.deleteById(email);
  }

  @Override
  public UserDataVO updateUser(String email, CreateUserVO updatedUserVO) {
      User existingUser = userRepository.findById(email)
              .orElseThrow(() -> new RuntimeException("User not found"));
      existingUser.setFirstName(updatedUserVO.firstName());
      existingUser.setLastName(updatedUserVO.lastName());
      existingUser.setEnabled(true);
      if (updatedUserVO.password() != null && !updatedUserVO.password().isBlank()) {
          String encryptedPassword = passwordEncoder.encode(updatedUserVO.password());
          existingUser.setPassword(encryptedPassword);
      }
      if (updatedUserVO.photo() != null && updatedUserVO.photo().photo() != null) {
          Optional<ImageUser> existingPhoto = imageUserRepository.findByUser(existingUser);

          if (existingPhoto.isPresent()) {
              ImageUser photoToUpdate = existingPhoto.get();
              photoToUpdate.setPhoto(updatedUserVO.photo().photo());
              photoToUpdate.setName(updatedUserVO.photo().name());
              imageUserRepository.save(photoToUpdate);
          } else {
              ImageUser newPhoto = ImageUser.builder()
                      .photo(updatedUserVO.photo().photo())
                      .name(updatedUserVO.photo().name())
                      .isPrincipal(true)
                      .user(existingUser)
                      .build();
              imageUserRepository.save(newPhoto);
          }
      }
      User updatedUser = userRepository.save(existingUser);
      return new UserDataVO(
              updatedUser.getEmail(),
              updatedUser.getFirstName(),
              updatedUser.getLastName(),
              updatedUser.getEnabled(),
              updatedUser.getRoles().get(0).getName(),
              new PhotoVO(imageUserRepository.findByUser(updatedUser).orElse(new ImageUser()).getPhoto(), imageUserRepository.findByUser(updatedUser).orElse(new ImageUser()).getName()));
  }
}
