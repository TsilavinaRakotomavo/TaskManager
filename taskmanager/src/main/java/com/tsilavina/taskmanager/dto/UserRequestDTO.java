package com.tsilavina.taskmanager.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserRequestDTO(
    @NotBlank @Pattern(regexp = "^[\\p{Lu}].*$", message = "Le nom doit commencer par une majuscule") String name,
    @NotBlank @Email String email,
    @NotBlank @Size(min = 6) String password) {
}