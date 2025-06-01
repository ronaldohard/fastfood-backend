package br.com.fiap.postech.grupo5.fastfood.adapter.outbound.entity.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tipo_produto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipo_produto_id_seq")
    @SequenceGenerator(name = "tipo_produto_id_seq", sequenceName = "tipo_produto_id_seq", allocationSize = 1)
    private Long id;
    private String nome;
}