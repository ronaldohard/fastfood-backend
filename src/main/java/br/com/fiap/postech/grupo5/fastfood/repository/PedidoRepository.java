package br.com.fiap.postech.grupo5.fastfood.repository;

import br.com.fiap.postech.grupo5.fastfood.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    Optional<Pedido> findByStatus(String status);

}
