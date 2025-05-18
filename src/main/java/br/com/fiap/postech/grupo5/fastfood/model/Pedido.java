package br.com.fiap.postech.grupo5.fastfood.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pedido_id_seq")
    @SequenceGenerator(name = "pedido_id_seq", sequenceName = "pedido_id_seq", allocationSize = 1)
    private Long id;
    private Long clienteId;
    private LocalDateTime data;
    private BigDecimal valorTotal;
    private String status;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedido")
    private List<ItemPedido> itens;

    public BigDecimal calcularTotal() {
        return itens.stream()
                .map(ItemPedido::calcularSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public String toString() {
        return "";
    }
}
