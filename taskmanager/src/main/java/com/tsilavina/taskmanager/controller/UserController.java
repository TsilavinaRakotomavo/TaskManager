package com.tsilavina.taskmanager.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tsilavina.taskmanager.dto.UserRequestDTO;
import com.tsilavina.taskmanager.dto.UserResponseDTO;
import com.tsilavina.taskmanager.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public List<UserResponseDTO> getUsers() {
    return userService.getAllUsers();
  }

  @PostMapping
  public UserResponseDTO createUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
    return userService.createUser(userRequestDTO);
  }

  @GetMapping("/{id}")
  public UserResponseDTO getUserById(@PathVariable int id) {
    return userService.getUserById(id);
  }

  @DeleteMapping("/{id}")
  public void deleteUser(@PathVariable int id) {
    userService.deleteUser(id);
  }
}
