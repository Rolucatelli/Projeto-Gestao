package com.example.bluevelvetmusicstore.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.example.bluevelvetmusicstore.enums.UserRole;
import com.example.bluevelvetmusicstore.model.entities.User;
import com.example.bluevelvetmusicstore.model.vo.UserDataVO;
import com.example.bluevelvetmusicstore.repository.UserRepository;
import com.example.bluevelvetmusicstore.service.user.UserServiceImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class UserServiceTest {

  @Mock private UserRepository userRepositoryMock;

  @InjectMocks private UserServiceImpl userService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void givenUserEmail_whenFindByEmail_thenReturnUserDataVO() {
    // Arrange
    User user = new User();
    user.setFirstName("John");
    user.setLastName("Doe");
    user.setEnabled(true);
    user.setEmail("email@email.com");
    when(userRepositoryMock.findById("email@email.com")).thenReturn(Optional.of(user));

    // Act
    UserDataVO response = userService.findUserByEmail("email@email.com");

    // Assert
    assertEquals("John", response.firstName());
    assertEquals("Doe", response.lastName());
    assertTrue(response.enabled());
    assertEquals("email@email.com", response.email());
  }

  @Test
  public void givenInvalidUserEmail_whenFindByEmail_thenThrowException() {
    // Arrange
    when(userRepositoryMock.findById("email@email.com")).thenReturn(Optional.empty());

    // Act
    RuntimeException runtimeException =
        assertThrows(RuntimeException.class, () -> userService.findUserByEmail("email@email.com"));

    // Assert
    assertEquals("User not found", runtimeException.getMessage());
  }

  @Test
  public void givenPageObject_whenRetrieveAllUsers_thenReturnUserDataVOPage() {
    // Arrange
    User user1 = new User();
    user1.setFirstName("Caio");

    User user2 = new User();
    user2.setFirstName("Dan");

    List<User> users = Arrays.asList(user1, user2);
    Page<User> usersPage = new PageImpl<>(users, PageRequest.of(0, 2), users.size());

    when(userRepositoryMock.findAllByRoleName(anyString(), any(Pageable.class)))
        .thenReturn(usersPage);

    // Act
    Page<UserDataVO> response =
        userService.retrieveAllUsers(UserRole.ADMINISTRATOR, PageRequest.of(0, 2));

    // Assert
    assertNotNull(response);
    assertEquals(2, response.getTotalElements());
    assertEquals(user1.getFirstName(), response.getContent().get(0).firstName());
    assertEquals(user2.getFirstName(), response.getContent().get(1).firstName());
  }
}
