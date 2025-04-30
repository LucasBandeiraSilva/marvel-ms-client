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

    @PostMapping("/register-hero")
    public ResponseEntity registerHero(@RequestBody HeroAgentRequestDTO heroAgentRequestDTO){
        try{
            HeroRegistrationProtocol heroRegistrationProtocol = heroService.registerHero(heroAgentRequestDTO);
            return ResponseEntity.status(HttpStatus.OK).body(heroRegistrationProtocol);
        }catch (HeroRegistrationException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
