package br.com.fiap.postech.grupo5.fastfood.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldNameConstants
public class ItemPedidoDTO {
    private Long produtoId;
    private Long pedidoId;
    private Integer quantidade;
    private List<CustomizacaoDTO> customizacoes;

}
