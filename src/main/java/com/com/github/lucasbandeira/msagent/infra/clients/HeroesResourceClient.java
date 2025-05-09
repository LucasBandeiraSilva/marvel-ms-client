package com.com.github.lucasbandeira.msagent.infra.clients;

import com.com.github.lucasbandeira.msagent.model.dto.HeroResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@FeignClient(value = "msmarvel", path = "/hero")
public interface HeroesResourceClient {

    @GetMapping("/status")
    String status();

    @GetMapping(params = "hero-code")
    ResponseEntity <HeroResponseDTO> getHeroByCode( @RequestParam("hero-code") String heroCode );

    @GetMapping
    ResponseEntity <List <HeroResponseDTO>> findAll();

    @DeleteMapping("/{id}")
    ResponseEntity<Void>deleteHero(@PathVariable UUID id);

    @GetMapping("/by-agent/{agentCode}")
    ResponseEntity<List<HeroResponseDTO>> getHeroesByAgent(@PathVariable String agentCode);

}
