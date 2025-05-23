package br.com.fiap.postech.grupo5.fastfood.adapter.controller;

import br.com.fiap.postech.grupo5.fastfood.dto.MercadoPagoResponse;
import br.com.fiap.postech.grupo5.fastfood.dto.PaymentDTO;
import br.com.fiap.postech.grupo5.fastfood.dto.PedidoDTO;
import br.com.fiap.postech.grupo5.fastfood.service.PagamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pagamentos")
@RequiredArgsConstructor
@Tag(name = "1 - Pagamento", description = "Operações de Pagamento")
public class CheckoutPaymentController {

    private final PagamentoService service;

    @Operation(summary = "Reposta da confirmação de pagamento")
    @PostMapping("/mock/response/qr-code")
    public ResponseEntity<PaymentDTO> qrcode(@RequestBody PedidoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.processarPagamento(dto));
    }

    @Operation(summary = "Criar novo pagamento via QR CODE")
    @PostMapping("/mock/request/qr-code")
    public ResponseEntity<MercadoPagoResponse> criar(@RequestBody PedidoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                MercadoPagoResponse
                        .builder()
                        .qrCode("mock_qr_code")
                        .qrCodeBase64("qr_code_base_64")
                        .build());
    }


}