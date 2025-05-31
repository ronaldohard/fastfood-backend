package br.com.fiap.postech.grupo5.fastfood.adapter.controller;

import br.com.fiap.postech.grupo5.fastfood.adapter.client.MercadoPagoClientAdapter;
import br.com.fiap.postech.grupo5.fastfood.dto.MercadoPagoResponse;
import br.com.fiap.postech.grupo5.fastfood.dto.QrCodeResponseDTO;
import br.com.fiap.postech.grupo5.fastfood.service.PagamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pagamentos")
@RequiredArgsConstructor
@Tag(name = "2 - Pagamento", description = "Operações de Pagamento já Confirmado para Pedido")
public class PagamentotController {

    private final PagamentoService service;
    private final MercadoPagoClientAdapter mercadoPagoClientAdapter;

    @Operation(summary = "Solicitar pagamento por QR Code", description = "Gera um QR Code para pagamento do pedido informado")
    @PostMapping("/qrcode")
    public ResponseEntity<QrCodeResponseDTO> gerarQrCode(@RequestParam Long pedidoId) {
        QrCodeResponseDTO response = service.gerarQrCodeParaPedido(pedidoId);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/confirmado")
    public ResponseEntity<Void> receberNotificacao(@RequestBody MercadoPagoResponse payload) {
        System.out.println("Recebeu notificação: " + payload);


        service.pagamentoConfirmado(payload);

        return ResponseEntity.ok().build();
    }

}