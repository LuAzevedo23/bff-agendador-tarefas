package com.luciene.bffagendadortarefas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableFeignClients(basePackages = "com.luciene.bffagendadortarefas.infrastructure.client")

public class BffAgendadorTarefasApplication {

	public static void main(String[] args) {

		SpringApplication.run(BffAgendadorTarefasApplication.class, args);
	}

}
