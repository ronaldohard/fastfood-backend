package br.com.fiap.postech.grupo5.fastfood.application.service;

import br.com.fiap.postech.grupo5.fastfood.adapter.outbound.client.MercadoPagoClientAdapter;
import br.com.fiap.postech.grupo5.fastfood.adapter.outbound.entity.order.Pedido;
import br.com.fiap.postech.grupo5.fastfood.adapter.outbound.entity.pagamento.Pagamento;
import br.com.fiap.postech.grupo5.fastfood.adapter.outbound.enums.status.StatusPedido;
import br.com.fiap.postech.grupo5.fastfood.adapter.outbound.repositories.PedidoRepository;
import br.com.fiap.postech.grupo5.fastfood.application.dto.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class PagamentoService {

    private final MercadoPagoClientAdapter mercadoPagoClientAdapter;
    private final PedidoRepository pedidoRepository;
    private final NotificarClienteService notificarClienteService;

    private static MercadoPagoRequest getMercadoPagoRequest(PedidoDTO pedido) {
        MercadoPagoRequest req = new MercadoPagoRequest();
        req.setDescription("Pedido #" + pedido.getId());
        req.setTransaction_amount(pedido.getValorTotal());
        req.setPayment_method_id("QR_CODE");

        var payer = new MercadoPagoRequest.Payer();
        payer.setEmail("cliente@email.com");
        var id = new MercadoPagoRequest.Payer.Identification();

        //todo - numero da senha talvez
        id.setNumber("12345678900");
        payer.setIdentification(id);

        req.setPayer(payer);
        return req;
    }

    public QrCodeResponseDTO gerarQrCodeParaPedido(Long pedidoId) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado"));

        if (!StatusPedido.AGUARDANDO_PAGAMENTO.name().equals(pedido.getStatus())) {
            throw new RuntimeException("Pedido não está apto para pagamento");
        }

        CriarPagamentoQrCodeDTO dto = new CriarPagamentoQrCodeDTO();
        dto.setTransactionAmount(pedido.getValorTotal());
        dto.setDescription("Pagamento pedido #" + pedido.getId());
        dto.setExternalReference("pedido_" + pedido.getId());

        var response = mercadoPagoClientAdapter.gerarQrCode(dto);

        return response;
    }

    public MercadoPagoResponse processarPagamento(PedidoDTO pedido) {
        MercadoPagoRequest req = getMercadoPagoRequest(pedido);

        MercadoPagoResponse response = mercadoPagoClientAdapter.criarPagamento(req);

        //todo - setar o response do checkout do pagamento no pedido
        // pedido.pagamento -> qrCode -> (response.getQrCodeBase64());

        //todo - acertar retorno
        return response;
    }

    public void pagamentoConfirmado(MercadoPagoResponse mercadoPagoResponse) {

        //todo - seta o pagamento (confirmado) no pedido
        log.info("Gravando o pagamento confirmado no pedido..");
        Pedido pedido = pedidoRepository.findById(Long.valueOf(mercadoPagoResponse.getExternalReference()))
                .orElseThrow(() -> new EntityNotFoundException(""));

        Pagamento pagamento = new Pagamento();
        pagamento.setData(LocalDateTime.now());
        pagamento.setStatus("PAGO"); //todo - talvez nao precise
        pagamento.setValorTotal(pedido.getValorTotal());//todo - talvez nao precise
        pagamento.setQrCodeUrl(mercadoPagoResponse.getQrCode());

        pedido.setPagamento(pagamento);

        //todo - altera o status para EM_PREPARO
        log.info("Enviando o pedido para cozinha (status: EM_PREPARO...");
        pedido.setStatus("EM_PREPARO");

        this.notificarCliente(pedido);

        //todo - imprime comprovante
        log.info("Imprimindo comprovante...");


        //todo - Finaliza e inicia nova sessao
        log.info("Sessao finalizada...");
    }

    private void notificarCliente(Pedido pedido) {
        //todo - envia sms para cliente
        log.info("Verificando se o cliente esta cadastrado...");

        if (Objects.nonNull(pedido.getCliente())) {
            notificarClienteService.enviarSMS(pedido.getId(), pedido.getCliente().getCel());
            return;
        }

        log.info("Cliente nao cadastrado, visualizacao somente");

    }
}
