package com.example.linkedInProject.userService.respository;

import com.example.linkedInProject.userService.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    public boolean existsByEmail(String email);


    public User findByEmail(String email);
}
