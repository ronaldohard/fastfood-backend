package br.com.fiap.postech.grupo5.fastfood.adapter.outbound.repositories;

import br.com.fiap.postech.grupo5.fastfood.adapter.outbound.entity.product.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByTipoProdutoId(Long tipoProdutoId);
}
