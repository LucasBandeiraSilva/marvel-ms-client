package com.com.github.lucasbandeira.msagent.service;

import com.com.github.lucasbandeira.msagent.infra.HeroesResourceClient;
import com.com.github.lucasbandeira.msagent.model.HeroResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HeroService {

    private final HeroesResourceClient heroesResourceClient;

    public HeroResponseDTO getHeroStatus( String heroCode){
        return  heroesResourceClient.getHeroByCode(heroCode).getBody();
    }
}
