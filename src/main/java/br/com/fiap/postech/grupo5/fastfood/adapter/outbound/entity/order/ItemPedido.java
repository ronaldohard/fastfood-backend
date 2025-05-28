package br.com.fiap.postech.grupo5.fastfood.adapter.outbound.entity.order;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@Table(name = "pedido_item")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pedido_item_id_seq")
    @SequenceGenerator(name = "pedido_item_id_seq", sequenceName = "pedido_item_id_seq", allocationSize = 1)
    private Long id;

    //@Column(name = "produto_id")
    private Long produtoId;
    private Integer quantidade;
    private BigDecimal preco = BigDecimal.ONE;
//
//    @Column(name = "pedido_id")
//    private Long pedidoId;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne
    private Pedido pedido;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itemPedido")
    private List<Customizacao> customizacoes;

    public BigDecimal calcularSubtotal() {
        BigDecimal base = new BigDecimal("10.00");
        BigDecimal adicionais = customizacoes.stream()
                .filter(c -> c.getTipo().equals("addition"))
                .map(Customizacao::getPreco)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return base.add(adicionais).multiply(BigDecimal.valueOf(quantidade));
    }

    public String toString() {
        return "";
    }
}
