package br.com.fiap.postech.grupo5.fastfood.mapper;

import br.com.fiap.postech.grupo5.fastfood.dto.CustomizacaoDTO;
import br.com.fiap.postech.grupo5.fastfood.dto.ItemPedidoDTO;
import br.com.fiap.postech.grupo5.fastfood.dto.PedidoDTO;
import br.com.fiap.postech.grupo5.fastfood.model.Customizacao;
import br.com.fiap.postech.grupo5.fastfood.model.ItemPedido;
import br.com.fiap.postech.grupo5.fastfood.model.Pedido;
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
public class PedidoMapperImpl implements PedidoMapper {

    @Override
    public Pedido toEntity(PedidoDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Pedido pedido = new Pedido();

        linkItems( pedido );

        return pedido;
    }

    @Override
    public PedidoDTO toDto(Pedido dto) {
        if ( dto == null ) {
            return null;
        }

        PedidoDTO pedidoDTO = new PedidoDTO();

        return pedidoDTO;
    }

    @Override
    public ItemPedido toItemPedido(ItemPedidoDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ItemPedido itemPedido = new ItemPedido();

        return itemPedido;
    }

    @Override
    public Customizacao toCustomizacao(CustomizacaoDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Customizacao customizacao = new Customizacao();

        return customizacao;
    }

    @Override
    public List<ItemPedido> toItemPedidoList(List<ItemPedidoDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<ItemPedido> list1 = new ArrayList<ItemPedido>( list.size() );
        for ( ItemPedidoDTO itemPedidoDTO : list ) {
            list1.add( toItemPedido( itemPedidoDTO ) );
        }

        return list1;
    }

    @Override
    public List<Customizacao> toCustomizacaoList(List<CustomizacaoDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Customizacao> list1 = new ArrayList<Customizacao>( list.size() );
        for ( CustomizacaoDTO customizacaoDTO : list ) {
            list1.add( toCustomizacao( customizacaoDTO ) );
        }

        return list1;
    }
}
