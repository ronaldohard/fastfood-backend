package br.com.fiap.postech.grupo5.fastfood.repository;

import br.com.fiap.postech.grupo5.fastfood.model.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {
}
