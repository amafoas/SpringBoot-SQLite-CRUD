package CRUD.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import CRUD.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  // There is no need to add a custom method in this case,
  // since JpaRepository provides basic CRUD methods.
}
