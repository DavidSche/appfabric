package com.davidche.appfabric.uaa.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("Auth/User API").description(
                        "Backend API For the Auth/User Service using springdoc-openapi and OpenAPI 3.").contact(new Contact().email("davidche@outlook.com").url("https://davidsche.github.io/"))
                        .license(new License().name("Apache 2.0").url("https://davidsche.github.io/")));
//        return new OpenAPI().components(new Components()).info(new Info().title("Hotel Booking Application API")
//                .description("This is a sample Spring Boot RESTful broker used for Hotel Booking and Demo of JWT"));
//        return new OpenAPI()
//                .components(new Components().addSecuritySchemes("basicScheme",
//                        new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic")))
//                .info(new Info().title("Contact API").version("v1")
//                        .description("This app provides REST APIs for Contact entity")
//                        .contact(new Contact().email("vishaljadhav@api.com").url("http://vishalapi.com/support"))
//                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
    }

    @Bean
    public GroupedOpenApi authApi(){
        return GroupedOpenApi.builder()
                .group("Authentication")
                .pathsToMatch("/api/auth/**")
                .packagesToScan("com.davidche.appfabric.uaa.controller")
                .build();
    }
    @Bean
    public GroupedOpenApi userApi(){
        return GroupedOpenApi.builder()
                .group("Users")
                .pathsToMatch("/api/user/**")
                .packagesToScan("com.davidche.appfabric.uaa.controller")
                .build();
    }

}
