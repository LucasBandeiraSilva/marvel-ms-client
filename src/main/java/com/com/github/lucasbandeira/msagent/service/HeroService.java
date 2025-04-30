package com.com.github.lucasbandeira.msagent.service;

import com.com.github.lucasbandeira.msagent.exception.HeroNotFoundException;
import com.com.github.lucasbandeira.msagent.infra.clients.HeroesResourceClient;
import com.com.github.lucasbandeira.msagent.infra.mqueue.RequestHeroRegistrationPublisher;
import com.com.github.lucasbandeira.msagent.model.HeroRegistrationProtocol;
import com.com.github.lucasbandeira.msagent.model.dto.HeroAgentRequestDTO;
import com.com.github.lucasbandeira.msagent.model.dto.HeroRequestDTO;
import com.com.github.lucasbandeira.msagent.model.dto.HeroResponseDTO;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HeroService {

    private final HeroesResourceClient heroesResourceClient;
    private final RequestHeroRegistrationPublisher heroRegistrationPublisher;


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
            heroRegistrationPublisher.RegisterHero(heroAgentRequestDTO);
            var protocol = UUID.randomUUID().toString();
            return new HeroRegistrationProtocol(protocol);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
