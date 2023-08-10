package com.assignment.group7.config;

import com.assignment.group7.dto.SwaggerProps;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Autowired
    private SwaggerProps swaggerProps;

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title(swaggerProps.getTitle())
                        .description(swaggerProps.getDescription())
                        .version(swaggerProps.getVersion())
                );
    }
}
