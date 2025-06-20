package br.com.fiap.postech.grupo5.fastfood.adapter.outbound.repositories;

import br.com.fiap.postech.grupo5.fastfood.adapter.outbound.entity.client.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByCpf(String cpf);

}
