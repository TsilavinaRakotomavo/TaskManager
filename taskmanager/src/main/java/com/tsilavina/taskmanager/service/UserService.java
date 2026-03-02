package com.tsilavina.taskmanager.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tsilavina.taskmanager.dto.UserRequestDTO;
import com.tsilavina.taskmanager.dto.UserResponseDTO;
import com.tsilavina.taskmanager.entity.Role;
import com.tsilavina.taskmanager.entity.User;
import com.tsilavina.taskmanager.exception.EmailAlreadyUsedException;
import com.tsilavina.taskmanager.exception.ResourceNotFoundException;
import com.tsilavina.taskmanager.mapper.UserMapper;
import com.tsilavina.taskmanager.repository.UserRepository;

@Service
public class UserService {
  private final UserRepository userRepository;
  private final UserMapper userMapper;

  public UserService(UserRepository userRepository, UserMapper userMapper) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
  }

  public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
    String normalizedEmail = userRequestDTO.email() == null ? null : userRequestDTO.email().trim();
    if (userRepository.existsByEmail(normalizedEmail)) {
      throw new EmailAlreadyUsedException(normalizedEmail);
    }

    User user = userMapper.toEntity(userRequestDTO);
    user.setRole(Role.USER);
    User savedUser = userRepository.save(user);
    return userMapper.toResponse(savedUser);
  }

  public List<UserResponseDTO> getAllUsers() {
    List<User> users = userRepository.findAll();
    return users.stream()
        .map(userMapper::toResponse)
        .collect(Collectors.toList());
  }

  public UserResponseDTO getUserById(int id) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    return userMapper.toResponse(user);
  }

  public void deleteUser(int id) {
    if (!userRepository.existsById(id)) {
      throw new ResourceNotFoundException("User not found with id: " + id);
    }
    userRepository.deleteById(id);
  }
}
