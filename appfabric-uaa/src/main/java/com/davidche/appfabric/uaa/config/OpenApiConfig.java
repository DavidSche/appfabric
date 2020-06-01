package com.davidche.appfabric.uaa.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("Auth/User API").description(
                        "Backend API For the Auth/User Service using springdoc-openapi and OpenAPI 3."));
//        return new OpenAPI().components(new Components()).info(new Info().title("Hotel Booking Application API")
//                .description("This is a sample Spring Boot RESTful broker used for Hotel Booking and Demo of JWT"));

    }
}
