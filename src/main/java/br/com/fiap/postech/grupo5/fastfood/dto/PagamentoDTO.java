package br.com.fiap.postech.grupo5.fastfood.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PagamentoDTO {

    private Long id;
    private Long tipoPagamentoId;
    private LocalDateTime data;
    private BigDecimal valorTotal;
    private String status;
    private String qrCodeUrl;
    //private PedidoDTO pedido;
}