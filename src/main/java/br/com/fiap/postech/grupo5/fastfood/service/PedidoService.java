package br.com.fiap.postech.grupo5.fastfood.service;

import br.com.fiap.postech.grupo5.fastfood.dto.PedidoDTO;
import br.com.fiap.postech.grupo5.fastfood.mapper.PedidoMapper;
import br.com.fiap.postech.grupo5.fastfood.model.Pedido;
import br.com.fiap.postech.grupo5.fastfood.repository.PedidoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoMapper pedidoMapper;
    private final PedidoRepository pedidoRepository;
    private final PagamentoService pagamentoService;
    //private final QrCodeClient qrCodeClient;

    public void criarPedido(PedidoDTO request) {
        Pedido pedido = pedidoMapper.toEntity(request);
        pedido.setStatus("pending");
        pedido.setData(LocalDateTime.now());
        pedido.setValorTotal(pedido.calcularTotal());
        // String qrCodeUrl = qrCodeClient.gerarQrCode(pedido);
        // pedido.setQrCodeUrl(qrCodeUrl);

        pagamentoService.processarPagamento(request);

        Pedido p = pedidoRepository.save(pedido);
        System.out.println(p);
    }

    public PedidoDTO buscarPorStatus(String cpf) {
        Pedido pedido = pedidoRepository.findByStatus(cpf)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
        return pedidoMapper.toDto(pedido);
    }


}
