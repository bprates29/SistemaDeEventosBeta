package br.com.bprates.eventoslab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("br.com.bprates.eventoslab.repository") 
@EntityScan("br.com.bprates.eventoslab.model")
@SpringBootApplication
public class SistemaDeEventosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaDeEventosApplication.class, args);
	}

}
