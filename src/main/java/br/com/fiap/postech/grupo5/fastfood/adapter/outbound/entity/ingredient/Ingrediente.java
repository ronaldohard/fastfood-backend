package br.com.fiap.postech.grupo5.fastfood.adapter.outbound.entity.ingredient;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "ingrediente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ingrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ingrediente_id_seq")
    @SequenceGenerator(name = "ingrediente_id_seq", sequenceName = "ingrediente_id_seq", allocationSize = 1)
    private Long id;

    private String nome;

    private BigDecimal preco;
}