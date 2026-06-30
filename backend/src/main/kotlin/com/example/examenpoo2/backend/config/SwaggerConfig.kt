package com.example.examenpoo2.backend.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Bean
    fun customOpenAPI(): OpenAPI {
        val securitySchemeName = "X-Role-Header"
        return OpenAPI()
            .info(
                Info()
                    .title("Vocational Test API")
                    .version("1.0.0")
                    .description("API para la gestión del test de orientación vocacional. Use el botón 'Authorize' para simular su rol (ADMIN, EDITOR, STUDENT).")
                    .contact(
                        Contact()
                            .name("Repositorio GitHub")
                            .url("https://github.com/c04o/examen-poo2/")
                    )
            )
            .addSecurityItem(SecurityRequirement().addList(securitySchemeName))
            .components(
                Components()
                    .addSecuritySchemes(
                        securitySchemeName,
                        SecurityScheme()
                            .name("X-Role")
                            .type(SecurityScheme.Type.APIKEY)
                            .`in`(SecurityScheme.In.HEADER)
                            .description("Simulación de rol: Escriba ADMIN, EDITOR o STUDENT")
                    )
            )
    }
}
