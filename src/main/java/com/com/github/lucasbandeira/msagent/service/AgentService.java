package com.com.github.lucasbandeira.msagent.service;

import com.com.github.lucasbandeira.msagent.model.Agent;
import com.com.github.lucasbandeira.msagent.model.dto.AgentRequestDto;
import com.com.github.lucasbandeira.msagent.repository.AgentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AgentService {

    private final AgentRepository agentRepository;

    public Agent save( Agent agent ) {
        return agentRepository.save(agent);
    }

    public Agent getById( UUID id ) {
        return agentRepository.findById(id).orElseThrow(() -> new RuntimeException("Agent Not found"));
    }

    public void deleteAgent( UUID id ) {
        Agent agent = agentRepository.findById(id).orElseThrow(() -> new RuntimeException("Agent Not Found"));
        agentRepository.delete(agent);
    }

    public Optional <Agent> updateAgent( UUID id, AgentRequestDto agentRequestDto ) {
        return agentRepository.findById(id).map(existingAgent -> {
            Agent agent = Agent.fromDto(agentRequestDto);
            agent.setId(existingAgent.getId());
            return agentRepository.save(agent);
        });
    }
}
