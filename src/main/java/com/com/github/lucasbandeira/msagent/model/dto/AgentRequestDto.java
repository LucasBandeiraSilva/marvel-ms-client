package com.com.github.lucasbandeira.msagent.model.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record AgentRequestDto(
        @NotBlank(message = "You must provide an agent code!")
        @Column(unique = true)
        String agentCode,
        @NotBlank(message = "The name must not be null or blank!")
        String name,
        @NotBlank(message = "The e-mail must not be null or blank!")
        @Pattern(
                regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
                message = "You must provide a valid e-mail"
        )
                @Email
        String email,
        @NotNull(message = "The 'active' Field must not be null")
        boolean active) {
}
