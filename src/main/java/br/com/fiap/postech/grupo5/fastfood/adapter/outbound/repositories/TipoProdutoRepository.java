package br.com.fiap.postech.grupo5.fastfood.adapter.outbound.repositories;

import br.com.fiap.postech.grupo5.fastfood.adapter.outbound.entity.product.TipoProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoProdutoRepository extends JpaRepository<TipoProduto, Long> {
}
