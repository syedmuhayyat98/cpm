package com.example.cpm.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "CPM",
                version = "0.0",
                description = "API documentation for CPM",
                contact = @Contact(url = "", name = "Admin", email = "syedmuhayyat98@gmail.com")
        )
)
public class SwaggerConfig {
}
