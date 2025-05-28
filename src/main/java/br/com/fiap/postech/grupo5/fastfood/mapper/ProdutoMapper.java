package br.com.fiap.postech.grupo5.fastfood.mapper;

import br.com.fiap.postech.grupo5.fastfood.adapter.outbound.entity.product.Produto;
import br.com.fiap.postech.grupo5.fastfood.application.dto.ProdutoDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    ProdutoMapper INSTANCE = Mappers.getMapper(ProdutoMapper.class);

    Produto toEntity(ProdutoDTO dto);

    ProdutoDTO toDto(Produto produto);

    List<ProdutoDTO> toDtoList(List<Produto> produtos);
}