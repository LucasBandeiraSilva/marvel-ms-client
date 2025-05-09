package com.com.github.lucasbandeira.msagent.infra.mqueue;

import com.com.github.lucasbandeira.msagent.model.dto.AgentRequestDTO;
import com.com.github.lucasbandeira.msagent.model.dto.HeroAgentRequestDTO;
import com.com.github.lucasbandeira.msagent.model.dto.HeroRequestDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RequestHeroPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final Queue heroRegistrationQueue;
    private final Queue heroUpdateQueue;

    public void sendMessage( HeroAgentRequestDTO heroAgentRequestDTO )throws JsonProcessingException{
        var json = convertIntoJson(heroAgentRequestDTO);
        rabbitTemplate.convertAndSend(heroRegistrationQueue.getName(),json);
    }

    public void updateHero( HeroRequestDTO heroRequestDTO ) throws JsonProcessingException {
        var json = convertHeroIntoJson(heroRequestDTO);
        rabbitTemplate.convertAndSend(heroUpdateQueue.getName(),json);
    }



    private String convertIntoJson(HeroAgentRequestDTO heroAgentRequestDTO) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        var json = mapper.writeValueAsString(heroAgentRequestDTO);
        return json;

    }
    private String convertHeroIntoJson(HeroRequestDTO heroRequestDTO) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        var json = mapper.writeValueAsString(heroRequestDTO);
        return json;
    }


}
