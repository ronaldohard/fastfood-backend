package br.com.fiap.postech.grupo5.fastfood.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${mercadopago.token}")
    private String mercadoPagoToken;

    @Bean
    public WebClient mercadoPagoWebClient() {
        return WebClient.builder()
                .baseUrl("https://api.mercadopago.com")
                .defaultHeader("Authorization", "Bearer " + mercadoPagoToken)
                .build();
    }
}