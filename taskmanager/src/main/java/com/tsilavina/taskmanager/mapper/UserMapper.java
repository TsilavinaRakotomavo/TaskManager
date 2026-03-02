package com.tsilavina.taskmanager.mapper;

import org.springframework.stereotype.Component;

import com.tsilavina.taskmanager.dto.UserRequestDTO;
import com.tsilavina.taskmanager.dto.UserResponseDTO;
import com.tsilavina.taskmanager.entity.User;

@Component
public class UserMapper {

  public User toEntity(UserRequestDTO dto) {
    if (dto == null) {
      return null;
    }

    User user = new User();
    user.setName(trim(dto.name()));
    user.setEmail(trim(dto.email()));
    user.setPassword(dto.password());
    return user;
  }

  public UserResponseDTO toResponse(User user) {
    if (user == null) {
      return null;
    }

    String role = user.getRole() != null ? user.getRole().name() : null;
    return new UserResponseDTO(
        user.getId(),
        user.getEmail(),
        user.getName(),
        role);
  }

  private String trim(String value) {
    return value == null ? null : value.trim();
  }
}
