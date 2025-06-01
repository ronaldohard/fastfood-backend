package br.com.fiap.postech.grupo5.fastfood.adapter.inbound.web.mappers;

import br.com.fiap.postech.grupo5.fastfood.adapter.outbound.entity.pagamento.Pagamento;
import br.com.fiap.postech.grupo5.fastfood.application.dto.PagamentoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PagamentoMapper {

    PagamentoMapper INSTANCE = Mappers.getMapper(PagamentoMapper.class);

    Pagamento toMap(PagamentoDTO dto);

    PagamentoDTO toMap(Pagamento pagamento);

}
