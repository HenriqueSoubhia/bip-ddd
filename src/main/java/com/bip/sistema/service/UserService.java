package com.bip.sistema.service;

import com.bip.sistema.model.User;
import com.bip.sistema.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // CREATE
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // READ
    public List<User> listAll() {
        return userRepository.findAll();
    }

    public User findById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public User findByBadge(Long badge) {
        return userRepository.findByBadgeCode(badge);
    }

    // UPDATE
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    // DELETE
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}
