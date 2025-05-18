package br.com.fiap.postech.grupo5.fastfood.dto;

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
    private BigDecimal valorTotal;
    private List<ItemPedidoDTO> produtos;
}