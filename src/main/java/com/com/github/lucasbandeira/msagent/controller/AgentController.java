package com.com.github.lucasbandeira.msagent.controller;

import com.com.github.lucasbandeira.msagent.controller.uriresolver.UriLocator;
import com.com.github.lucasbandeira.msagent.model.Agent;
import com.com.github.lucasbandeira.msagent.model.dto.AgentRequestDTO;
import com.com.github.lucasbandeira.msagent.model.dto.AgentResponseDTO;
import com.com.github.lucasbandeira.msagent.service.AgentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/agent")
@Slf4j
public class AgentController implements UriLocator {

    private final AgentService agentService;

    @GetMapping("/status")
    public String status() {
        log.info("Getting status");
        return "Ok!";
    }

    @PostMapping
    public ResponseEntity<Void> saveAgent( @RequestBody @Valid AgentRequestDTO agentRequestDto ){
        var agent = Agent.fromDto(agentRequestDto);
        agentService.save(agent);
        return ResponseEntity.created(generateHeaderLocation(agent.getId())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgentResponseDTO> getById( @PathVariable UUID id ){
        Agent agent = agentService.getById(id);
        return ResponseEntity.ok(AgentResponseDTO.fromEntity(agent));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deleteAgent(@PathVariable UUID id){
        agentService.deleteAgent(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void>updateAgent(@PathVariable UUID id, @RequestBody AgentRequestDTO agentRequestDto ){
        agentService.updateAgent(id,agentRequestDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
