package br.com.fiap.postech.grupo5.fastfood.mapper;

import br.com.fiap.postech.grupo5.fastfood.dto.IngredienteDTO;
import br.com.fiap.postech.grupo5.fastfood.model.Ingrediente;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IngredienteMapper {

    IngredienteMapper INSTANCE = Mappers.getMapper(IngredienteMapper.class);

    Ingrediente toEntity(IngredienteDTO dto);

    IngredienteDTO toDto(Ingrediente Ingrediente);

    List<IngredienteDTO> toDtoList(List<Ingrediente> Ingredientes);
}