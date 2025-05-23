package br.com.fiap.postech.grupo5.fastfood.service;

import br.com.fiap.postech.grupo5.fastfood.dto.ClienteDTO;
import br.com.fiap.postech.grupo5.fastfood.mapper.ClienteMapper;
import br.com.fiap.postech.grupo5.fastfood.model.Cliente;
import br.com.fiap.postech.grupo5.fastfood.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;
    private final ClienteMapper mapper;

    public ClienteDTO salvar(ClienteDTO dto) {
        Cliente entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }

    public List<ClienteDTO> listar() {
        return mapper.toDtoList(repository.findAll());
    }

    public ClienteDTO buscarPorId(Long id) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
        return mapper.toDto(cliente);
    }

    public ClienteDTO buscarPorCpf(String cpf) {
        Cliente cliente = repository.findByCpf(cpf)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
        return mapper.toDto(cliente);
    }

    public ClienteDTO atualizar(Long id, ClienteDTO dto) {
        Cliente existente = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
        existente.setId(dto.getId());
        existente.setNome(dto.getNome());
        existente.setCpf(dto.getCpf());
        existente.setCel(dto.getCel());
        return mapper.toDto(repository.save(existente));
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}

