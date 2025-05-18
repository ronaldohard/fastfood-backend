package br.com.fiap.postech.grupo5.fastfood.service;

import br.com.fiap.postech.grupo5.fastfood.dto.ProdutoDTO;
import br.com.fiap.postech.grupo5.fastfood.mapper.ProdutoMapper;
import br.com.fiap.postech.grupo5.fastfood.model.Produto;
import br.com.fiap.postech.grupo5.fastfood.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository repository;
    private final ProdutoMapper mapper;

    public ProdutoDTO salvar(ProdutoDTO dto) {
        Produto entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }

    public List<ProdutoDTO> listar() {
        return mapper.toDtoList(repository.findAll());
    }

    public ProdutoDTO buscarPorId(Long id) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
        return mapper.toDto(produto);
    }

    public ProdutoDTO atualizar(Long id, ProdutoDTO dto) {
        Produto existente = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
        existente.setNome(dto.getNome());
        existente.setPreco(dto.getPreco());
        existente.setTipoProdutoId(dto.getTipoProdutoId());
        return mapper.toDto(repository.save(existente));
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
