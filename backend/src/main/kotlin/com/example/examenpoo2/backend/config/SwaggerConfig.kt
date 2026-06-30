package com.example.examenpoo2.backend.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.Contact
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("Vocational Test API")
                    .version("1.0.0")
                    .description("API para la gestión del test de orientación vocacional.")
                    .contact(
                        Contact()
                            .name("Repositorio GitHub")
                            .url("https://github.com/c04o/examen-poo2/")
                    )
            )
    }
}
