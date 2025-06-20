package br.com.fiap.postech.grupo5.fastfood.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ApiError {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
    private List<FieldValidationError> errors;

    @Data
    @Builder
    public static class FieldValidationError {
        private String field;
        private String message;
    }
}
