package br.com.fiap.postech.grupo5.fastfood.adapter.inbound.web.mappers;

import br.com.fiap.postech.grupo5.fastfood.adapter.outbound.entity.ingredient.Ingrediente;
import br.com.fiap.postech.grupo5.fastfood.application.dto.IngredienteDTO;
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