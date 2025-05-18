package br.com.fiap.postech.grupo5.fastfood.mapper;

import br.com.fiap.postech.grupo5.fastfood.dto.ClienteDTO;
import br.com.fiap.postech.grupo5.fastfood.model.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

    Cliente toEntity(ClienteDTO dto);

    ClienteDTO toDto(Cliente cliente);

    List<ClienteDTO> toDtoList(List<Cliente> clientes);
}