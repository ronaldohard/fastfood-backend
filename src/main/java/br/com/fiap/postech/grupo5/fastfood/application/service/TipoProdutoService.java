package br.com.fiap.postech.grupo5.fastfood.application.service;

import br.com.fiap.postech.grupo5.fastfood.adapter.inbound.web.mappers.TipoProdutoMapper;
import br.com.fiap.postech.grupo5.fastfood.adapter.outbound.entity.product.TipoProduto;
import br.com.fiap.postech.grupo5.fastfood.adapter.outbound.repositories.TipoProdutoRepository;
import br.com.fiap.postech.grupo5.fastfood.application.dto.TipoProdutoDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoProdutoService {

    private final TipoProdutoRepository repository;
    private final TipoProdutoMapper mapper;

    public TipoProdutoDTO salvar(TipoProdutoDTO dto) {
        TipoProduto entity = mapper.toMap(dto);
        return mapper.toMap(repository.save(entity));
    }

    public List<TipoProdutoDTO> listar() {
        return mapper.toMap(repository.findAll());
    }

    public TipoProdutoDTO buscarPorId(Long id) {
        TipoProduto produto = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TipoProduto não encontrado"));
        return mapper.toMap(produto);
    }

    public TipoProdutoDTO atualizar(Long id, TipoProdutoDTO dto) {
        TipoProduto existente = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TipoProduto não encontrado"));
        existente.setNome(dto.getNome());
        return mapper.toMap(repository.save(existente));
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
