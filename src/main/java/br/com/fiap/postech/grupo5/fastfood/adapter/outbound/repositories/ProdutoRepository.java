package br.com.fiap.postech.grupo5.fastfood.adapter.outbound.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.postech.grupo5.fastfood.adapter.outbound.entity.product.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByTipoProdutoId(Long tipoProdutoId);
}
