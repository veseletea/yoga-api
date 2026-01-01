package com.example.yoga.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI yogaApiOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Yoga API")
                        .description("REST API pentru gestionarea unui catalog de poziții yoga")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("veseletea")
                                .url("https://github.com/veseletea/yoga-api"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("Development Server")));
    }
}
