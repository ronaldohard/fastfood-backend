package br.com.fiap.postech.grupo5.fastfood.adapter.outbound.entity.order;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "pedido_item_customizacao")
public class Customizacao {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pedido_item_customizacao_id_seq")
    @SequenceGenerator(name = "pedido_item_customizacao_id_seq", sequenceName = "pedido_item_customizacao_id_seq", allocationSize = 1)
    private Long id;
    private Long ingredienteId;
    private String tipo;
    private BigDecimal preco = BigDecimal.ZERO;

    @ManyToOne
    @JoinColumn(name = "pedido_item_id")
    private ItemPedido itemPedido;
}
