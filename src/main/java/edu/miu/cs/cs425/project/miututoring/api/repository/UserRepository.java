package edu.miu.cs.cs425.project.miututoring.api.repository;

import edu.miu.cs.cs425.project.miututoring.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}