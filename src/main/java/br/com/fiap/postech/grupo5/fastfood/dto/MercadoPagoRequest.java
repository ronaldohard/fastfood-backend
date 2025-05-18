package br.com.fiap.postech.grupo5.fastfood.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MercadoPagoRequest {
    private String description;
    private BigDecimal transaction_amount;
    private String payment_method_id;
    private Payer payer;

    @Data
    public static class Payer {
        private String email;
        private Identification identification;

        @Data
        public static class Identification {
            private String type = "CPF";
            private String number;
        }
    }
}