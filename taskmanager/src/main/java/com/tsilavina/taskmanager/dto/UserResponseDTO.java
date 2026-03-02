package com.tsilavina.taskmanager.dto;

public record UserResponseDTO(
    int id,
    String email,
    String name,
    String role) {
}
