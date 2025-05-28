package br.com.fiap.postech.grupo5.fastfood.adapter.outbound.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.postech.grupo5.fastfood.adapter.outbound.entity.ingredient.Ingrediente;

@Repository
public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {
}
