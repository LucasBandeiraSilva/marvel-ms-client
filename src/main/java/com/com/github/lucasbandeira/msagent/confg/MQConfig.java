package com.com.github.lucasbandeira.msagent.confg;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MQConfig {

    @Value("${mq.queues.hero-registration}")
    private String registrationQueue;

    @Value("${mq.queues.hero-update}")
    private String updateQueue;

    @Bean
    public Queue heroRegistrationQueue(){
        return new Queue(registrationQueue,true);
    }

    @Bean
    public Queue heroUpdateQueue(){return new Queue(updateQueue,true);}
}
