package com.com.github.lucasbandeira.msagent.service;

import com.com.github.lucasbandeira.msagent.exception.HeroNotFoundException;
import com.com.github.lucasbandeira.msagent.infra.clients.HeroesResourceClient;
import com.com.github.lucasbandeira.msagent.infra.mqueue.RequestHeroPublisher;
import com.com.github.lucasbandeira.msagent.model.HeroRegistrationProtocol;
import com.com.github.lucasbandeira.msagent.model.dto.HeroAgentRequestDTO;
import com.com.github.lucasbandeira.msagent.model.dto.HeroRequestDTO;
import com.com.github.lucasbandeira.msagent.model.dto.HeroResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HeroService {

    private final HeroesResourceClient heroesResourceClient;
    private final RequestHeroPublisher heroRegistrationPublisher;


    public HeroResponseDTO getHeroStatus( String heroCode){
        try{
            return heroesResourceClient.getHeroByCode(heroCode).getBody();
        }
        catch (FeignException.NotFound e) {
            throw new HeroNotFoundException("The Hero was not found with the code: " + heroCode);
        }
    }

    public HeroRegistrationProtocol registerHero( HeroAgentRequestDTO heroAgentRequestDTO ){
        try{
            heroRegistrationPublisher.sendMessage(heroAgentRequestDTO);
            var protocol = UUID.randomUUID().toString();
            return new HeroRegistrationProtocol(protocol);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public HeroRegistrationProtocol updateHero(String heroCode, HeroRequestDTO heroRequestDTO){
        try {
            heroRegistrationPublisher.updateHero(heroRequestDTO);
            var protocol = UUID.randomUUID().toString();
            return new HeroRegistrationProtocol(protocol);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    public List<HeroResponseDTO>getHeroesByAgent(String agentCode){
        return heroesResourceClient.getHeroesByAgent(agentCode).getBody();
    }

    public List<HeroResponseDTO> findAll(){
        return heroesResourceClient.findAll().getBody();
    }

    public void deleteHero(UUID id){
        try{
            heroesResourceClient.deleteHero(id).getBody();

        } catch (FeignException.NotFound e) {
            throw new HeroNotFoundException("Hero not found. Deletion cannot be completed");
        }
    }
}
