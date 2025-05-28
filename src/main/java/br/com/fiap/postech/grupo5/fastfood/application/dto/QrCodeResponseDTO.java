package br.com.fiap.postech.grupo5.fastfood.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Builder
public class QrCodeResponseDTO {
    private String id;
    private String qrData;
    private String externalReference;
    private String status;
}