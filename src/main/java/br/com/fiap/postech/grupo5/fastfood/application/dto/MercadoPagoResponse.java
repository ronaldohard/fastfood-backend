package br.com.fiap.postech.grupo5.fastfood.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MercadoPagoResponse {

    private String id;
    private String status;

    @JsonProperty("qr_code")
    private String qrCode;

    @JsonProperty("qr_code_base64")
    private String qrCodeBase64;
}
