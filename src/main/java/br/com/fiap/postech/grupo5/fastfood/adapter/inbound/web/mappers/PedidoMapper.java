package br.com.fiap.postech.grupo5.fastfood.adapter.inbound.web.mappers;

import br.com.fiap.postech.grupo5.fastfood.adapter.outbound.entity.order.Customizacao;
import br.com.fiap.postech.grupo5.fastfood.adapter.outbound.entity.order.ItemPedido;
import br.com.fiap.postech.grupo5.fastfood.adapter.outbound.entity.order.Pedido;
import br.com.fiap.postech.grupo5.fastfood.application.dto.CustomizacaoDTO;
import br.com.fiap.postech.grupo5.fastfood.application.dto.ItemPedidoDTO;
import br.com.fiap.postech.grupo5.fastfood.application.dto.MonitorDTO;
import br.com.fiap.postech.grupo5.fastfood.application.dto.PedidoDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {
        PagamentoMapper.class,
        ClienteMapper.class
})
public interface PedidoMapper {

    PedidoMapper INSTANCE = Mappers.getMapper(PedidoMapper.class);

    @Mapping(target = "status", source = "status")
    Pedido toMap(PedidoDTO dto);

    MonitorDTO toMonitor(Pedido entity);

    MonitorDTO toMonitor(PedidoDTO dto);

    //
    PedidoDTO toMap(Pedido dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customizacoes", source = "customizacoes")
    ItemPedido toItemPedido(ItemPedidoDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "itemPedido", ignore = true)
    @Mapping(target = "preco", constant = "0.0")
        // valor default, pode ser ajustado no service
    Customizacao toCustomizacao(CustomizacaoDTO dto);

    List<ItemPedido> toItemPedidoList(List<ItemPedidoDTO> list);

    List<Customizacao> toCustomizacaoList(List<CustomizacaoDTO> list);

    // Pós-processamento para setar vínculos bidirecionais
    @AfterMapping
    default void linkItems(@MappingTarget Pedido pedido) {
        if (pedido.getProdutos() != null) {
            for (ItemPedido item : pedido.getProdutos()) {
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
