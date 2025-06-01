package br.com.fiap.postech.grupo5.fastfood.application.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoDTO {

    private Long id;

    private PagamentoDTO pagamento;
    private ClienteDTO cliente;

    @NotEmpty(message = "Cliente (id) nao pode ser nulo")
    private Long clienteId;

    @NotEmpty(message = "Status nao pode ser nulo/vazio")
    private String status;

    @NotNull(message = "Valor total nao pode ser nulo ou zero")
    private BigDecimal valorTotal;

    @NotNull(message = "lista de itens não pode ser nula")
    private List<ItemPedidoDTO> produtos;
}