package br.com.fiap.postech.grupo5.fastfood.application.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CriarPagamentoQrCodeDTO {
    private BigDecimal transactionAmount;
    private String description;
    private String externalReference;
}
