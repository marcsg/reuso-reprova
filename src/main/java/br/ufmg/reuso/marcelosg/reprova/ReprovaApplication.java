package br.ufmg.reuso.marcelosg.reprova;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class ReprovaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReprovaApplication.class, args);
	}

}
