package com.com.github.lucasbandeira.msagent.model;

import com.com.github.lucasbandeira.msagent.model.dto.AgentRequestDTO;
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
    private boolean active;


    public Agent( String agentCode, String name, boolean active ) {
        this.agentCode = agentCode;
        this.name = name;
        this.active = active;
    }

    public static Agent fromDto( AgentRequestDTO agentRequestDto ) {
        return new Agent(agentRequestDto.agentCode(), agentRequestDto.name(), agentRequestDto.active());
    }
}
