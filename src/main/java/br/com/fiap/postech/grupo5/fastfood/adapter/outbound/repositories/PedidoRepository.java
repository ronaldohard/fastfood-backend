package br.com.fiap.postech.grupo5.fastfood.adapter.outbound.repositories;

import br.com.fiap.postech.grupo5.fastfood.adapter.outbound.entity.order.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    Optional<Pedido> findByStatus(String status);

    List<Pedido> findAllByStatusIn(Collection<String> statuses);
}
