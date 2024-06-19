package es.laspalmeras.padel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.bind.annotation.RestController;

@EntityScan
@RestController
@EnableAutoConfiguration
@SpringBootApplication
public class PadelApplication {

	public static void main(String[] args) {
		SpringApplication.run(PadelApplication.class, args);
	}

}
