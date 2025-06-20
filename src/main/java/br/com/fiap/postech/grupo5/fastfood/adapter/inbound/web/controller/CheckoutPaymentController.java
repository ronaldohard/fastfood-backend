package br.com.fiap.postech.grupo5.fastfood.adapter.inbound.web.controller;

import br.com.fiap.postech.grupo5.fastfood.adapter.outbound.client.MercadoPagoClientAdapter;
import br.com.fiap.postech.grupo5.fastfood.application.service.PagamentoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/checkout-pagamento")
@RequiredArgsConstructor
@Tag(name = "Checkout Pagamento (mock)", description = "Operações de Pagamento via Mercado Pago (QRCODE)")
public class CheckoutPaymentController {

    private final PagamentoService service;
    private final MercadoPagoClientAdapter mercadoPagoClientAdapter;

    // @Operation(summary = "Resposta da confirmação de pagamento")
    // @PostMapping("/{id}/confirmar")
    // public ResponseEntity<Void> confirmarPagamento(@PathVariable String id) {
    //     // Simula chamada para sua API interna
    //     Map<String, Object> notificacao = new HashMap<>();
    //     notificacao.put("id", id);
    //     notificacao.put("status", "approved");

    //     //todo - servico mock
    //     mercadoPagoClientAdapter.confirmar("http://localhost:8080/api/pagamentos/notificacao", notificacao, Void.class);

    //     return ResponseEntity.ok().build();
    // }



}