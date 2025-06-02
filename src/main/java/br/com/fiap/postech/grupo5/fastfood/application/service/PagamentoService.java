package br.com.fiap.postech.grupo5.fastfood.application.service;

import br.com.fiap.postech.grupo5.fastfood.adapter.outbound.client.MercadoPagoClientAdapter;
import br.com.fiap.postech.grupo5.fastfood.adapter.outbound.entity.order.Pedido;
import br.com.fiap.postech.grupo5.fastfood.adapter.outbound.entity.pagamento.Pagamento;
import br.com.fiap.postech.grupo5.fastfood.adapter.outbound.enums.Status;
import br.com.fiap.postech.grupo5.fastfood.adapter.outbound.enums.TipoPagamento;
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
        log.info("Gerando QR Code para Pedido #{}", pedidoId);

        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado"));

        if (!Status.AGUARDANDO_PAGAMENTO.name().equals(pedido.getStatus())) {
            throw new RuntimeException("Pedido não está apto para pagamento");
        }

        CriarPagamentoQrCodeDTO dto = new CriarPagamentoQrCodeDTO();
        dto.setTransactionAmount(pedido.getValorTotal());
        dto.setDescription("Pagamento pedido #" + pedido.getId());
        dto.setExternalReference(String.valueOf(pedido.getId()));

        return mercadoPagoClientAdapter.gerarQrCode(dto);
    }

    public MercadoPagoResponse processarPagamento(PedidoDTO pedido) {
        log.info("Processar Pagamento do pedido #{}", pedido.getId());

        MercadoPagoRequest req = getMercadoPagoRequest(pedido);

        MercadoPagoResponse response = mercadoPagoClientAdapter.criarPagamento(req);

        //todo - setar o response do checkout do pagamento no pedido
        // pedido.pagamento -> qrCode -> (response.getQrCodeBase64());

        //todo - acertar retorno
        return response;
    }

    public void pagamentoConfirmado(MercadoPagoResponse mercadoPagoResponse) {
        log.info("Gravando o pagamento confirmado no pedido..");
        Pedido pedido = pedidoRepository.findById(Long.valueOf(mercadoPagoResponse.getExternalReference()))
                .orElseThrow(() -> new EntityNotFoundException(""));

        Pagamento pagamento = new Pagamento();
        pagamento.setData(LocalDateTime.now());
        pagamento.setTipoPagamentoId(TipoPagamento.QR_CODE.getId());
        pagamento.setQrCodeUrl(mercadoPagoResponse.getQrCode());
        pagamento.setStatus("PAGO");
        pagamento.setValorTotal(pedido.getValorTotal());
        pagamento.setPedido(pedido);
        pedido.setPagamento(pagamento);

        //todo - altera o status para EM_PREPARACAO
        log.info("Enviando o pedido para cozinha (status: EM_PREPARACAO...");
        pedido.setStatus(Status.EM_PREPARACAO.name());

        log.info("Atualizado o pedido #{}...", pedido.getId());
        pedidoRepository.save(pedido);
        log.info("Pedido atualizado com sucesso...");

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
