package es.laspalmeras.padel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(info = @Info(
	    title = "API de Padel",
	    version = "1.0",
	    description = "Documentación de la API para gestionar campeonatos de pádel"
))
@SpringBootApplication
@ComponentScan(basePackages = {"es.laspalmeras.padel"})
public class PadelApplication {

	public static void main(String[] args) {
		SpringApplication.run(PadelApplication.class, args);
	}

}
