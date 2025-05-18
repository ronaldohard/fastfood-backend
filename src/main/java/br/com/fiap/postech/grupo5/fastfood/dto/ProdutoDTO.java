package br.com.fiap.postech.grupo5.fastfood.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldNameConstants
public class ProdutoDTO {
    private String nome;
    private Long tipoProdutoId;
    private BigDecimal preco;
}
