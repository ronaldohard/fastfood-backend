package br.com.fiap.postech.grupo5.fastfood.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI fastFoodOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("🍔 FastFood - API do Totem de Pedidos (11SOAT - Grupo 05)")
                        .description("Sistema acadêmico de pedidos de lanchonete com integração a pagamentos via QR Code")
                        .version("1.0.0"));
    }
}
