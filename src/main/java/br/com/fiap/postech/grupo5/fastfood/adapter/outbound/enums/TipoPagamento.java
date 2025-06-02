package br.com.fiap.postech.grupo5.fastfood.adapter.outbound.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
public enum TipoPagamento {
    QR_CODE(1L);

    @Getter
    private Long id;
}
