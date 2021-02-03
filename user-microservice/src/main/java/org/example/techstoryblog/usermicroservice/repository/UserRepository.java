package org.example.techstoryblog.usermicroservice.repository;

import org.example.techstoryblog.usermicroservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameAndPasswordOrEmailAndPassword(String username, String password, String email, String passwordDup);
}
