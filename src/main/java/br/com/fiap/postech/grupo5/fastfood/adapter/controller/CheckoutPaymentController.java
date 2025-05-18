package br.com.fiap.postech.grupo5.fastfood.adapter.controller;

import br.com.fiap.postech.grupo5.fastfood.dto.PaymentDTO;
import br.com.fiap.postech.grupo5.fastfood.dto.PedidoDTO;
import br.com.fiap.postech.grupo5.fastfood.service.PagamentoService;
import io.swagger.v3.oas.annotations.Operation;
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
public class CheckoutPaymentController {

    private final PagamentoService service;

    @Operation(summary = "Criar novo produto")
    @PostMapping
    public ResponseEntity<PaymentDTO> criar(@RequestBody PedidoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.processarPagamento(dto));
    }

}