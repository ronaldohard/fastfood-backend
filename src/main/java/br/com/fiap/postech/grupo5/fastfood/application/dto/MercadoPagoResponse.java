package br.com.fiap.postech.grupo5.fastfood.application.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * {
 * "id": "abc123",
 * "qr_code": "https://qr.fake/abc123",
 * "external_reference": "pedido_42",
 * "status": "pending"
 * }
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MercadoPagoResponse {

    private String id;
    private String qrCode;
    private String externalReference;
    private String status;
}
