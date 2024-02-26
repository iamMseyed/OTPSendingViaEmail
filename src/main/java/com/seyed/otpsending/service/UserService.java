    package com.seyed.otpsending.service;

import com.seyed.otpsending.entity.User;
import com.seyed.otpsending.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository= userRepository;
    }

    public User registeruser(User user){
        //save user to db
        return userRepository.save(user);
    }

    public User getUserByEmail(String email) {
      return userRepository.findByEmail(email);
    }

    public void verifyEmail(User user) {
        user.setEmailVerified(true);
        userRepository.save(user);
    }

    public boolean isEmailVerified(String email) {
        User user = userRepository.findByEmail(email);
        return user!=null && user.isEmailVerified();
    }
}
