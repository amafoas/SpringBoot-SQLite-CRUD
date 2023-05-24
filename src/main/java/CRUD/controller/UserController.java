package CRUD.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import CRUD.service.UserService;
import CRUD.entity.User;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping
  public ResponseEntity<User> addUser(@RequestBody User user) {
    try {
      User addedUser = userService.addUser(user);
      return ResponseEntity.status(HttpStatus.CREATED).body(addedUser);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
  }

  @GetMapping("/{id}")
  public User getUser(@PathVariable Long id) {
    return userService.getUserById(id);
  }

  @PutMapping("/{id}")
  public User updateUser(@PathVariable Long id, @RequestBody User user) {
    return userService.updateUser(id, user);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    boolean deleted = userService.deleteUser(id);
    if (deleted) {
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping
  public List<User> getAllUsers() {
    return userService.getAllUsers();
  }
}
