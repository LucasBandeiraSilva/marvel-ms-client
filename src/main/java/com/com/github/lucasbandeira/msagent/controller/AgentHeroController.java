package com.com.github.lucasbandeira.msagent.controller;

import com.com.github.lucasbandeira.msagent.exception.HeroRegistrationException;
import com.com.github.lucasbandeira.msagent.model.HeroRegistrationProtocol;
import com.com.github.lucasbandeira.msagent.model.dto.HeroAgentRequestDTO;
import com.com.github.lucasbandeira.msagent.model.dto.HeroRequestDTO;
import com.com.github.lucasbandeira.msagent.model.dto.HeroResponseDTO;
import com.com.github.lucasbandeira.msagent.service.HeroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/agent/hero")
public class AgentHeroController {

    private final HeroService heroService;

    @GetMapping(params = "hero-code")
    public ResponseEntity <HeroResponseDTO> heroSituationCheck( @RequestParam("hero-code")String heroCode){
        HeroResponseDTO hero = heroService.getHeroStatus(heroCode);
        return ResponseEntity.status(HttpStatus.OK).body(hero);
    }

    @GetMapping("/by-agent/{agentCode}")
    public ResponseEntity<List<HeroResponseDTO>> getHeroesByAgent(@PathVariable String agentCode){
        return ResponseEntity.status(HttpStatus.OK).body(heroService.getHeroesByAgent(agentCode));
    }

    @GetMapping
    public ResponseEntity<List<HeroResponseDTO>>getAllHeroes(){
        return ResponseEntity.status(HttpStatus.OK).body(heroService.findAll());
    }

    @PostMapping("/register-hero")
    public ResponseEntity registerHero(@RequestBody HeroAgentRequestDTO heroAgentRequestDTO){
        try{
            HeroRegistrationProtocol heroRegistrationProtocol = heroService.registerHero(heroAgentRequestDTO);
            return ResponseEntity.status(HttpStatus.OK).body(heroRegistrationProtocol);
        }catch (HeroRegistrationException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/{heroCode}")
    public ResponseEntity updateHero(@PathVariable String heroCode, @RequestBody HeroRequestDTO heroRequestDTO){
        try{
        HeroRegistrationProtocol heroRegistrationProtocol = heroService.updateHero(heroCode,heroRequestDTO);
            return ResponseEntity.status(HttpStatus.OK).body(heroRegistrationProtocol);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deleteHero( @PathVariable UUID id ){
        heroService.deleteHero(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
