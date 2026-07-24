package com.fooddelivery.food_delivery_backend.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI foodDeliveryAPI() {

        return new OpenAPI()

                .info(new Info()

                        .title("Food Delivery Backend API")

                        .description(
                                "Production Style Food Delivery Backend")

                        .version("v1.0"))

                .externalDocs(

                        new ExternalDocumentation()

                                .description("Project Documentation")

                                .url("https://github.com"));
    }
}