package br.com.fiap.postech.grupo5.fastfood.adapter.client;

import br.com.fiap.postech.grupo5.fastfood.dto.MercadoPagoRequest;
import br.com.fiap.postech.grupo5.fastfood.dto.MercadoPagoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MercadoPagoClientAdapter {

    private final WebClient mercadoPagoWebClient;

    public MercadoPagoResponse criarPagamento(MercadoPagoRequest req) {
        return /*mercadoPagoWebClient.post()
                .uri("/v1/payments")
                .bodyValue(req)
                .retrieve()
                .bodyToMono(MercadoPagoResponse.class)
                .block();*/

                MercadoPagoResponse.builder()
                        .id(UUID.randomUUID().toString())
                        .status("PAID")
                        .qrCode("___qrCode___")
                        .qrCodeBase64("___qrCodeBase64___")
                        .build();
    }
}