package com.com.github.lucasbandeira.msagent.model.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record AgentRequestDTO(
        @NotBlank(message = "You must provide an agent code!")
        @Column(unique = true)
        String agentCode,
        @NotBlank(message = "The name must not be null or blank!")
        String name,
        @NotNull(message = "The 'active' Field must not be null")
        boolean active) {
}
