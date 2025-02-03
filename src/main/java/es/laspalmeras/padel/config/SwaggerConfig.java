package es.laspalmeras.padel.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    // Define la configuración general de OpenAPI para tu API.
    @Bean
    protected OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Padel")
                        .version("1.0")
                        .description("Documentación de la API para gestionar campeonatos de pádel")
                        .contact(new Contact()
                                .name("Eduardo")
                                .email("edujg81@gmail.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")));
    }

    // Configura un grupo de APIs, en este caso se agrupan aquellas rutas que empiecen por /api
    @Bean
    protected GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("padel")
                .pathsToMatch("/public/**")
                .build();
    }
}