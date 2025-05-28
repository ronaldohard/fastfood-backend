package br.com.fiap.postech.grupo5.fastfood.adapter.outbound.repositories;

import br.com.fiap.postech.grupo5.fastfood.adapter.outbound.entity.ingredient.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {
}
