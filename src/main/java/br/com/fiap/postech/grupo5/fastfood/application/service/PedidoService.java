package br.com.fiap.postech.grupo5.fastfood.application.service;

import br.com.fiap.postech.grupo5.fastfood.adapter.inbound.web.mappers.PedidoMapper;
import br.com.fiap.postech.grupo5.fastfood.adapter.outbound.entity.order.Pedido;
import br.com.fiap.postech.grupo5.fastfood.adapter.outbound.enums.Status;
import br.com.fiap.postech.grupo5.fastfood.adapter.outbound.repositories.PedidoRepository;
import br.com.fiap.postech.grupo5.fastfood.application.dto.MonitorDTO;
import br.com.fiap.postech.grupo5.fastfood.application.dto.PedidoDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PedidoService {

    private final PedidoMapper pedidoMapper;
    private final PedidoRepository pedidoRepository;
    private final PagamentoService pagamentoService;

    public void criarPedido(PedidoDTO request) {
        log.info("Criando pedido...");
        Pedido pedido = pedidoMapper.toMap(request);
        pedido.setStatus(Status.AGUARDANDO_PAGAMENTO.name());
        pedido.setData(LocalDateTime.now());
        pedido.setValorTotal(pedido.calcularTotal());

        pagamentoService.processarPagamento(request);

        Pedido p = pedidoRepository.save(pedido);
        log.info("Pedido criado... {}", p.getId());
    }

    public PedidoDTO buscarPorStatus(String cpf) {
        log.info("Buscar por ");
        return pedidoRepository.findByStatus(cpf)
                .map(pedidoMapper::toMap)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado por status"));
    }


    public PedidoDTO buscarPorId(Long id) {
        return pedidoRepository.findById(id)
                .map(pedidoMapper::toMap)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado por id"));
    }

    public List<PedidoDTO> buscarTodos() {
        return pedidoRepository.findAll(Sort.by(Sort.Direction.DESC, "id"))
                .stream()
                .map(pedidoMapper::toMap)
                .toList()
                ;
    }

    public List<MonitorDTO> buscarPedidosMonitor() {
        return pedidoRepository
                .findAllByStatusInOrderByDataAsc(List.of(Status.EM_PREPARACAO.name(), Status.PRONTO.name()))
                .stream()
                .map(pedidoMapper::toMonitor)
                .toList();
    }

    public PedidoDTO alterarStatus(Long id, String status) {
        log.info("Alterando status para: {}", status);
        return pedidoRepository.findById(id)
                .map(p -> {
                    p.setStatus(status);
                    return p;
                })
                .map(pedidoRepository::save)
                .map(pedidoMapper::toMap)
                .orElseThrow(() -> new EntityNotFoundException("Pedido nao encontrado."))
                ;


    }
}
