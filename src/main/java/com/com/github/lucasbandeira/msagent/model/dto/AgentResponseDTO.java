package com.com.github.lucasbandeira.msagent.model.dto;

import com.com.github.lucasbandeira.msagent.model.Agent;

public record AgentResponseDTO(String agentCode, String name, boolean active) {

    public static AgentResponseDTO fromEntity( Agent agent ){
        return new AgentResponseDTO(agent.getAgentCode(),agent.getName(), agent.isActive());
    }
}
