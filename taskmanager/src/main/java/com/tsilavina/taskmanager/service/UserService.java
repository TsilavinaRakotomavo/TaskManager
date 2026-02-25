package com.tsilavina.taskmanager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tsilavina.taskmanager.entity.User;
import com.tsilavina.taskmanager.repository.UserRepository;

@Service
public class UserService {
  private UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User createUser(User user) {
    return userRepository.save(user);
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public User getUserById(int id) {
    return userRepository.findById(id).orElse(null);
  }

  public void deleteUser(int id) {
    userRepository.deleteById(id);
  }
}
