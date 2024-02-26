package com.seyed.otpsending.repository;

import com.seyed.otpsending.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email); //have various build in methods, but here we create this abstract method to get user details based on mail provided
}