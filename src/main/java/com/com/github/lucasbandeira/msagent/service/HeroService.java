package com.com.github.lucasbandeira.msagent.service;

import com.com.github.lucasbandeira.msagent.exception.HeroNotFoundException;
import com.com.github.lucasbandeira.msagent.infra.HeroesResourceClient;
import com.com.github.lucasbandeira.msagent.model.dto.HeroResponseDTO;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HeroService {

    private final HeroesResourceClient heroesResourceClient;

    public HeroResponseDTO getHeroStatus( String heroCode){

        try{
            return heroesResourceClient.getHeroByCode(heroCode).getBody();
        }

        catch (FeignException.NotFound e) {
            throw new HeroNotFoundException("The Hero was not found with the code: " + heroCode);
        }

    }
}
