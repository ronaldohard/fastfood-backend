package br.com.fiap.postech.grupo5.fastfood.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pagamento")
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pagamento_id_seq")
    @SequenceGenerator(name = "pagamento_id_seq", sequenceName = "pagamento_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "tipo_pagamento_id")
    private Long tipoPagamentoId;

    @Column(name = "criado_em")
    private LocalDateTime data;

    @Column(name = "valor_total")
    private BigDecimal valorTotal;
    private String status;

    @Column(name = "qr_code_url")
    private String qrCodeUrl;


    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToOne
    @JoinColumn(name = "pedido_id", unique = true)
    private Pedido pedido;

}
