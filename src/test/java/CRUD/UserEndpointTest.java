package CRUD;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import CRUD.entity.User;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserEndpointTest {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void createUserTest() {
    User newUser = new User("Jane Smith", "janesmith@example.com");

    ResponseEntity<User> response = restTemplate.postForEntity(
        "http://localhost:" + port + "/users",
        newUser,
        User.class);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());

    User createdUser = response.getBody();
    assertEquals("Jane Smith", createdUser.getName());
    assertEquals("janesmith@example.com", createdUser.getEmail());
  }

  @Test
  public void readUserTest() {
    // Crear un usuario de prueba en la base de datos
    User newUser = new User("John Doe", "johndoe@example.com");
    User createdUser = restTemplate.postForObject(
        "http://localhost:" + port + "/users",
        newUser,
        User.class);

    // Obtener el usuario recién creado
    ResponseEntity<User> response = restTemplate.getForEntity(
        "http://localhost:" + port + "/users/" + createdUser.getId(),
        User.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());

    User retrievedUser = response.getBody();
    assertEquals("John Doe", retrievedUser.getName());
    assertEquals("johndoe@example.com", retrievedUser.getEmail());
  }

  @Test
  public void updateUserTest() {
    // Crear un usuario de prueba en la base de datos
    User newUser = new User("John Doe", "johndoe@example.com");
    User createdUser = restTemplate.postForObject(
        "http://localhost:" + port + "/users",
        newUser,
        User.class);

    // Actualizar el usuario
    User updateUser = new User("Updated Name", "updatedemail@example.com");

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    ResponseEntity<User> response = restTemplate.exchange(
        "http://localhost:" + port + "/users/" + createdUser.getId(),
        HttpMethod.PUT,
        new HttpEntity<>(updateUser, headers),
        User.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());

    User updatedUser = response.getBody();
    assertEquals("Updated Name", updatedUser.getName());
    assertEquals("updatedemail@example.com", updatedUser.getEmail());
  }

  @Test
  public void deleteUserTest() {
    // Crear un usuario de prueba en la base de datos
    User newUser = new User("John Doe", "johndoe@example.com");
    User createdUser = restTemplate.postForObject(
        "http://localhost:" + port + "/users",
        newUser,
        User.class);

    // Eliminar el usuario
    ResponseEntity<Void> response = restTemplate.exchange(
        "http://localhost:" + port + "/users/" + createdUser.getId(),
        HttpMethod.DELETE,
        null,
        Void.class);
    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
  }
}
