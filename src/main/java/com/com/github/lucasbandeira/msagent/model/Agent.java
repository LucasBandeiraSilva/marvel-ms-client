package com.com.github.lucasbandeira.msagent.model;

import com.com.github.lucasbandeira.msagent.model.dto.AgentRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "tb_agent")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String agentCode;
    private String name;
    private String email;
    private boolean active;


    public Agent( String agentCode, String name, String email, boolean active ) {
        this.agentCode = agentCode;
        this.name = name;
        this.email = email;
        this.active = active;
    }

    public static Agent fromDto( AgentRequestDto agentRequestDto ) {
        return new Agent(agentRequestDto.agentCode(), agentRequestDto.name(), agentRequestDto.email(), agentRequestDto.active());
    }
}
