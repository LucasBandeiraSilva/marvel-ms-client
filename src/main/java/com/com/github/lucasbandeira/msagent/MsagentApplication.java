package com.com.github.lucasbandeira.msagent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsagentApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsagentApplication.class, args);
	}

}
