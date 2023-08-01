package com.example.rest.repositroy;

import com.example.rest.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsernameAndPassword(String username, String password);
//    Optional<User> findByUsername(String username);
//    List<User> findAllByEmailIgnoreCase(String email);
    User findByUsername(String username);
}
