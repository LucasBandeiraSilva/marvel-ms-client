package com.com.github.lucasbandeira.msagent.infra;

import com.com.github.lucasbandeira.msagent.model.dto.HeroResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

    @FeignClient(value = "msmarvel",path = "/hero")
    public interface HeroesResourceClient {

        @GetMapping("/status")
        String status();

        @GetMapping(params = "hero-code")
        ResponseEntity<HeroResponseDTO> getHeroByCode( @RequestParam("hero-code")String heroCode);

    }
