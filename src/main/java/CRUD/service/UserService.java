package CRUD.service;

import java.util.List;

import org.springframework.stereotype.Service;

import CRUD.repository.UserRepository;
import CRUD.entity.User;
import CRUD.exception.UserNotFoundException;
import CRUD.exception.UserServiceException;

@Service
public class UserService {
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User addUser(User user) {
    try {
      return userRepository.save(user);
    } catch (Exception e) {
      throw new UserServiceException("Error adding the user");
    }
  }

  public User getUserById(Long userId) {
    return userRepository.findById(userId)
        .orElseThrow(() -> new UserNotFoundException("User not found ID: " + userId));
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public User updateUser(Long id, User updateUser) {
    userRepository.findById(id)
        .orElseThrow(() -> new UserNotFoundException("Invalid user ID: " + id));

    updateUser.setId(id);

    return userRepository.save(updateUser);
  }

  public boolean deleteUser(Long userId) {
    if (userRepository.existsById(userId)) {
      userRepository.deleteById(userId);
      return true;
    } else {
      return false;
    }
  }
}
