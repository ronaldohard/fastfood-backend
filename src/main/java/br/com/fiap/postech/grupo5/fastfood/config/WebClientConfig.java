package br.com.fiap.postech.grupo5.fastfood.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient mercadoPagoWebClient() {
        return WebClient.builder().build();
    }
}