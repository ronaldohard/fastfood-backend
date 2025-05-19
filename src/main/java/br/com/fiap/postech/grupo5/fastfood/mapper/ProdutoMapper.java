package br.com.fiap.postech.grupo5.fastfood.mapper;

import br.com.fiap.postech.grupo5.fastfood.dto.ProdutoDTO;
import br.com.fiap.postech.grupo5.fastfood.model.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    ProdutoMapper INSTANCE = Mappers.getMapper(ProdutoMapper.class);

    @Mapping(source = "preco",         target = "preco")
    @Mapping(source = "nome",         target = "nome")
    @Mapping(source = "tipoProdutoId",         target = "tipoProdutoId")
    Produto toEntity(ProdutoDTO dto);
    
    @Mapping(source = "preco",         target = "preco")
    @Mapping(source = "nome",         target = "nome")
    @Mapping(source = "tipoProdutoId",         target = "tipoProdutoId")
    ProdutoDTO toDto(Produto produto);

    List<ProdutoDTO> toDtoList(List<Produto> produtos);
}