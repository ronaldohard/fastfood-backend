package br.com.fiap.postech.grupo5.fastfood.mapper;

import br.com.fiap.postech.grupo5.fastfood.dto.CustomizacaoDTO;
import br.com.fiap.postech.grupo5.fastfood.dto.ItemPedidoDTO;
import br.com.fiap.postech.grupo5.fastfood.dto.PedidoDTO;
import br.com.fiap.postech.grupo5.fastfood.model.Customizacao;
import br.com.fiap.postech.grupo5.fastfood.model.ItemPedido;
import br.com.fiap.postech.grupo5.fastfood.model.Pedido;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PedidoMapper {

    PedidoMapper INSTANCE = Mappers.getMapper(PedidoMapper.class);

    //    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "data", ignore = true)
//    @Mapping(target = "status", ignore = true)
//    @Mapping(target = "valorTotal", ignore = true)
//    @Mapping(target = "itens", source = "produtos")
    Pedido toEntity(PedidoDTO dto);

    //
    PedidoDTO toDto(Pedido dto);

    //    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "customizacoes", source = "customizacoes")
    ItemPedido toItemPedido(ItemPedidoDTO dto);

    //    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "itemPedido", ignore = true)
//    @Mapping(target = "preco", constant = "0.0") // valor default, pode ser ajustado no service
    Customizacao toCustomizacao(CustomizacaoDTO dto);

    List<ItemPedido> toItemPedidoList(List<ItemPedidoDTO> list);

    List<Customizacao> toCustomizacaoList(List<CustomizacaoDTO> list);

    // Pós-processamento para setar vínculos bidirecionais
    @AfterMapping
    default void linkItems(@MappingTarget Pedido pedido) {
        if (pedido.getItens() != null) {
            for (ItemPedido item : pedido.getItens()) {
                item.setPedido(pedido);
                if (item.getCustomizacoes() != null) {
                    for (Customizacao c : item.getCustomizacoes()) {
                        c.setItemPedido(item);
                    }
                }
            }
        }
    }

}
