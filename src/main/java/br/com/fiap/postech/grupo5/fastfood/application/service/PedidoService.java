package br.com.fiap.postech.grupo5.fastfood.application.service;

import br.com.fiap.postech.grupo5.fastfood.adapter.outbound.entity.order.Pedido;
import br.com.fiap.postech.grupo5.fastfood.adapter.outbound.repositories.PedidoRepository;
import br.com.fiap.postech.grupo5.fastfood.application.dto.MonitorDTO;
import br.com.fiap.postech.grupo5.fastfood.application.dto.PedidoDTO;
import br.com.fiap.postech.grupo5.fastfood.mapper.PedidoMapper;
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
    //private final QrCodeClient qrCodeClient;

    public void criarPedido(PedidoDTO request) {
        Pedido pedido = pedidoMapper.toMap(request);
        pedido.setStatus("RECEIVED");
        pedido.setData(LocalDateTime.now());
        pedido.setValorTotal(pedido.calcularTotal());
        // String qrCodeUrl = qrCodeClient.gerarQrCode(pedido);
        // pedido.setQrCodeUrl(qrCodeUrl);

        pagamentoService.processarPagamento(request);

        Pedido p = pedidoRepository.save(pedido);
        System.out.println(p);
    }

    public PedidoDTO buscarPorStatus(String cpf) {
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
                 .findAllByStatusIn(List.of("IN_PREPARATION", "READY"))
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
