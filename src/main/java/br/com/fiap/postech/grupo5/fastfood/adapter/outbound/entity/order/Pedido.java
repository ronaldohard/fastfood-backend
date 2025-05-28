package br.com.fiap.postech.grupo5.fastfood.adapter.outbound.entity.order;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
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

    @Column(name = "cliente_id")
    private Long clienteId;
    private LocalDateTime data;
    private BigDecimal valorTotal;

    @NotEmpty
    private String status;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL)
    private Pagamento pagamento;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "cliente_id", insertable = false, updatable = false)
    @ManyToOne(cascade = CascadeType.ALL)
    private Cliente cliente;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedido")
    private List<ItemPedido> produtos;

    public BigDecimal calcularTotal() {
        return produtos.stream()
                .map(ItemPedido::calcularSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public String toString() {
        return "";
    }
}
