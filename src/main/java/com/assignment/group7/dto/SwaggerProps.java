package com.assignment.group7.dto;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "app.swagger")
public class SwaggerProps {
    private String title;
    private String description;
    private String version;
}
