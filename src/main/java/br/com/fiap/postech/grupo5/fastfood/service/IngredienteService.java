package br.com.fiap.postech.grupo5.fastfood.service;

import br.com.fiap.postech.grupo5.fastfood.dto.IngredienteDTO;
import br.com.fiap.postech.grupo5.fastfood.mapper.IngredienteMapper;
import br.com.fiap.postech.grupo5.fastfood.model.Ingrediente;
import br.com.fiap.postech.grupo5.fastfood.repository.IngredienteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredienteService {

    private final IngredienteRepository repository;
    private final IngredienteMapper mapper;

    public IngredienteDTO salvar(IngredienteDTO dto) {
        Ingrediente entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }

    public List<IngredienteDTO> listar() {
        return mapper.toDtoList(repository.findAll());
    }

    public IngredienteDTO buscarPorId(Long id) {
        Ingrediente Ingrediente = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ingrediente não encontrado"));
        return mapper.toDto(Ingrediente);
    }

    public IngredienteDTO atualizar(Long id, IngredienteDTO dto) {
        Ingrediente existente = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ingrediente não encontrado"));
        existente.setNome(dto.getNome());
        existente.setPreco(dto.getPreco());
        return mapper.toDto(repository.save(existente));
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
