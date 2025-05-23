package br.com.fiap.postech.grupo5.fastfood.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {

    private Long id;
    private Long clienteId;

    @NotEmpty(message = "Status nao pode ser nulo/vazio")
    private String status;
    private BigDecimal valorTotal;
    private List<ItemPedidoDTO> produtos;
}