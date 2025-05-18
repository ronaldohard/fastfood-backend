package br.com.fiap.postech.grupo5.fastfood.mapper;

import br.com.fiap.postech.grupo5.fastfood.dto.ClienteDTO;
import br.com.fiap.postech.grupo5.fastfood.model.Cliente;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-18T18:47:05-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.6 (Microsoft)"
)
@Component
public class ClienteMapperImpl implements ClienteMapper {

    @Override
    public Cliente toEntity(ClienteDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Cliente cliente = new Cliente();

        return cliente;
    }

    @Override
    public ClienteDTO toDto(Cliente cliente) {
        if ( cliente == null ) {
            return null;
        }

        ClienteDTO clienteDTO = new ClienteDTO();

        return clienteDTO;
    }

    @Override
    public List<ClienteDTO> toDtoList(List<Cliente> clientes) {
        if ( clientes == null ) {
            return null;
        }

        List<ClienteDTO> list = new ArrayList<ClienteDTO>( clientes.size() );
        for ( Cliente cliente : clientes ) {
            list.add( toDto( cliente ) );
        }

        return list;
    }
}
