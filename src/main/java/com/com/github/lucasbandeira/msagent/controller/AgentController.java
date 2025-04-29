package com.com.github.lucasbandeira.msagent.controller;

import com.com.github.lucasbandeira.msagent.controller.uriresolver.UriLocator;
import com.com.github.lucasbandeira.msagent.model.Agent;
import com.com.github.lucasbandeira.msagent.model.HeroResponseDTO;
import com.com.github.lucasbandeira.msagent.model.dto.AgentRequestDto;
import com.com.github.lucasbandeira.msagent.model.dto.AgentResponseDTO;
import com.com.github.lucasbandeira.msagent.service.AgentService;
import com.com.github.lucasbandeira.msagent.service.HeroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/agent")
public class AgentController implements UriLocator {

    private final AgentService agentService;
    private final HeroService heroService;

    @GetMapping("/status")
    public String status(){
        return "Ok!";
    }

    @PostMapping
    public ResponseEntity<Void> saveAgent( @RequestBody @Valid AgentRequestDto agentRequestDto ){
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
    public ResponseEntity<Void>updateAgent(@PathVariable UUID id, @RequestBody AgentRequestDto agentRequestDto ){
        agentService.updateAgent(id,agentRequestDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(value = "/hero",params = "hero-code")
    public ResponseEntity<HeroResponseDTO>heroSituationCheck( @RequestParam("hero-code")String heroCode){
        HeroResponseDTO hero = heroService.getHeroStatus(heroCode);
        return ResponseEntity.status(HttpStatus.OK).body(hero);
    }


}
