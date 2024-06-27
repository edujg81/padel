package es.laspalmeras.padel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"es.laspalmeras.padel"})
public class PadelApplication {

	public static void main(String[] args) {
		SpringApplication.run(PadelApplication.class, args);
	}

}
