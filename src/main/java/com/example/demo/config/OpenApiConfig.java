package com.example.demo.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    private static final String SECURITY_SCHEME_NAME = "bearerAuth";

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Contract Breach Penalty Calculator API")
                .version("1.0.0")
                .description(
                    "API for managing contracts, deliveries, breach rules, and penalty calculations"
                )
            )

            /*
             * IMPORTANT:
             * Use RELATIVE server URL.
             * This allows Swagger to automatically use:
             *  - https://9182.408procr.amypo.ai in production
             *  - http://localhost:8080 in local dev
             *
             * No hardcoding. No browser localhost bug.
             */
            .servers(List.of(
                new Server().url("/")
            ))

            .addSecurityItem(
                new SecurityRequirement().addList(SECURITY_SCHEME_NAME)
            )

            .components(new Components()
                .addSecuritySchemes(
                    SECURITY_SCHEME_NAME,
                    new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")
                )
            );
    }
}
