package br.com.fiap.postech.grupo5.fastfood.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonitorDTO {
    private String status;
    private Long id;
}