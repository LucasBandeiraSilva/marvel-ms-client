package com.com.github.lucasbandeira.msagent.repository;

import com.com.github.lucasbandeira.msagent.model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AgentRepository extends JpaRepository<Agent, UUID> {
}
