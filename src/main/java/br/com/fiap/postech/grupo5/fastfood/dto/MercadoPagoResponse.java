package br.com.fiap.postech.grupo5.fastfood.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MercadoPagoResponse {

    private Long id;
    private String status;

    @JsonProperty("qr_code")
    private String qrCode;

    @JsonProperty("qr_code_base64")
    private String qrCodeBase64;
}
