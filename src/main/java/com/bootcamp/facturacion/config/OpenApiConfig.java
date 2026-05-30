package com.bootcamp.facturacion.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.servers.ServerVariable;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API de Facturación",
                description = "Sistema de facturación - Documentación de endpoints",
                version = "1.0.0",
                contact = @Contact(
                        name = "Soporte",
                        email = "soporte@facturacion.com"
                ),
                license = @License(
                        name = "MIT"
                )
        ),
        servers = {
                @Server(
                        description = "Servidor local",
                        url = "http://localhost:{port}",
                        variables = {
                                @ServerVariable(name = "port", defaultValue = "8080")
                        }
                )
        }
)
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT",
        description = "Token JWT obtenido al iniciar sesión"
)
public class OpenApiConfig {
}
