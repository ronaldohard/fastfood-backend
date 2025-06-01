package br.com.fiap.postech.grupo5.fastfood.adapter.outbound.client;

import br.com.fiap.postech.grupo5.fastfood.application.dto.CriarPagamentoQrCodeDTO;
import br.com.fiap.postech.grupo5.fastfood.application.dto.MercadoPagoRequest;
import br.com.fiap.postech.grupo5.fastfood.application.dto.MercadoPagoResponse;
import br.com.fiap.postech.grupo5.fastfood.application.dto.QrCodeResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MercadoPagoClientAdapter {

    private final WebClient webClient;

    public MercadoPagoResponse criarPagamento(MercadoPagoRequest req) {
        return /*mercadoPagoWebClient.post()
                .uri("/v1/payments")
                .bodyValue(req)
                .retrieve()
                .bodyToMono(MercadoPagoResponse.class)
                .block();*/

                MercadoPagoResponse.builder()
                        .id(UUID.randomUUID().toString())
                        .status("pending")
                        .qrCode("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAA...")
                        .externalReference("id_pedido")
                        .build();
    }

    public void confirmar(String url, Map<String, Object> notificacao, Class<Void> voidClass) {
        webClient.post()
                .uri(url)
                .bodyValue(notificacao)
                .retrieve()
                .bodyToMono(Void.class)
                .block()
        ;
    }

    public QrCodeResponseDTO gerarQrCode(CriarPagamentoQrCodeDTO dto) {
        //todo - chamar servico do mercado pago
        return QrCodeResponseDTO.builder()
                .id(UUID.randomUUID().toString())
                .qrData("https://qr.fake/abc123")
                .externalReference(dto.getExternalReference())
                .status("AGUARDANDO_PAGAMENTO")
                .build();
    }
}