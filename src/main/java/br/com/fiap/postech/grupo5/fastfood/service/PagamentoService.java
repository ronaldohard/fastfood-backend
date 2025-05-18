package br.com.fiap.postech.grupo5.fastfood.service;

import br.com.fiap.postech.grupo5.fastfood.adapter.client.MercadoPagoClientAdapter;
import br.com.fiap.postech.grupo5.fastfood.dto.MercadoPagoRequest;
import br.com.fiap.postech.grupo5.fastfood.dto.MercadoPagoResponse;
import br.com.fiap.postech.grupo5.fastfood.dto.PaymentDTO;
import br.com.fiap.postech.grupo5.fastfood.dto.PedidoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PagamentoService {

    private final MercadoPagoClientAdapter mercadoPagoClientAdapter;

    public PaymentDTO processarPagamento(PedidoDTO pedido) {
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

        MercadoPagoResponse response = mercadoPagoClientAdapter.criarPagamento(req);

        //todo - setar o response do checkout do pagamento no pedido
        // pedido.pagamento -> qrCode -> (response.getQrCodeBase64());

        //todo - acertar retorno
        return PaymentDTO.builder().build();
    }


}
