package br.com.fiap.postech.grupo5.fastfood.adapter.inbound.web.mappers;

import br.com.fiap.postech.grupo5.fastfood.adapter.outbound.entity.product.TipoProduto;
import br.com.fiap.postech.grupo5.fastfood.application.dto.TipoProdutoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TipoProdutoMapper {

    TipoProdutoMapper INSTANCE = Mappers.getMapper(TipoProdutoMapper.class);

    TipoProduto toMap(TipoProdutoDTO dto);

    TipoProdutoDTO toMap(TipoProduto tipoProduto);

    List<TipoProdutoDTO> toMap(List<TipoProduto> tipoProdutos);
}