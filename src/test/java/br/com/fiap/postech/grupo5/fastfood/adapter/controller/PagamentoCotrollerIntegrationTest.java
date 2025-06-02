package br.com.fiap.postech.grupo5.fastfood.adapter.controller;

import br.com.fiap.postech.grupo5.fastfood.adapter.outbound.entity.order.Pedido;
import br.com.fiap.postech.grupo5.fastfood.adapter.outbound.repositories.PedidoRepository;
import br.com.fiap.postech.grupo5.fastfood.application.dto.MercadoPagoResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PagamentoCotrollerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PedidoRepository pedidoRepository;

    private Long pedidoId;

    @BeforeEach
    void setup() {
        pedidoRepository.deleteAll();

        Pedido pedido = new Pedido();
        pedido.setValorTotal(new BigDecimal("50.00"));
        pedido.setStatus("AGUARDANDO_PAGAMENTO");
        pedido = pedidoRepository.saveAndFlush(pedido);
        pedidoId = pedido.getId();
    }

    @Test
    @DisplayName("POST /api/pagamentos/qrcode?pedidoId={id} → Gera QR Code retorna 200 e dados esperados")
    void testGerarQrCode() throws Exception {
        mockMvc.perform(post("/api/pagamentos/qrcode")
                        .param("pedidoId", String.valueOf(pedidoId))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").isString())
                .andExpect(jsonPath("$.qr_data").value("https://qr.fake/abc123"))
                .andExpect(jsonPath("$.external_reference").value("pedido_1"))
                .andExpect(jsonPath("$.status").value("AGUARDANDO_PAGAMENTO"));
    }
}
