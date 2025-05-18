package br.com.fiap.postech.grupo5.fastfood.mapper;

import br.com.fiap.postech.grupo5.fastfood.dto.IngredienteDTO;
import br.com.fiap.postech.grupo5.fastfood.model.Ingrediente;
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
public class IngredienteMapperImpl implements IngredienteMapper {

    @Override
    public Ingrediente toEntity(IngredienteDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Ingrediente ingrediente = new Ingrediente();

        return ingrediente;
    }

    @Override
    public IngredienteDTO toDto(Ingrediente Ingrediente) {
        if ( Ingrediente == null ) {
            return null;
        }

        IngredienteDTO ingredienteDTO = new IngredienteDTO();

        return ingredienteDTO;
    }

    @Override
    public List<IngredienteDTO> toDtoList(List<Ingrediente> Ingredientes) {
        if ( Ingredientes == null ) {
            return null;
        }

        List<IngredienteDTO> list = new ArrayList<IngredienteDTO>( Ingredientes.size() );
        for ( Ingrediente ingrediente : Ingredientes ) {
            list.add( toDto( ingrediente ) );
        }

        return list;
    }
}
