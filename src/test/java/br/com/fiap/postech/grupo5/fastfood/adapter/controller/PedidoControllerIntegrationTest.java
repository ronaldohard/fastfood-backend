package br.com.fiap.postech.grupo5.fastfood.adapter.controller;

import br.com.fiap.postech.grupo5.fastfood.adapter.outbound.entity.client.Cliente;
import br.com.fiap.postech.grupo5.fastfood.adapter.outbound.entity.ingredient.Ingrediente;
import br.com.fiap.postech.grupo5.fastfood.adapter.outbound.entity.product.Produto;
import br.com.fiap.postech.grupo5.fastfood.adapter.outbound.entity.product.TipoProduto;
import br.com.fiap.postech.grupo5.fastfood.adapter.outbound.repositories.*;
import br.com.fiap.postech.grupo5.fastfood.application.dto.*;
import br.com.fiap.postech.grupo5.fastfood.application.service.*;
import com.fasterxml.jackson.core.type.TypeReference;
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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class PedidoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private TipoProdutoRepository tipoProdutoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private IngredienteRepository ingredienteRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    private Long clienteId;
    private Long produtoId1;
    private Long produtoId2;
    private Long ingredienteId1;
    private Long ingredienteId2;
    private Long ingredienteId3;
    private Long ingredienteId4;

    @BeforeEach
    void setup() {
        pedidoRepository.deleteAll();
        produtoRepository.deleteAll();
        tipoProdutoRepository.deleteAll();
        ingredienteRepository.deleteAll();
        clienteRepository.deleteAll();

        Cliente cliente = new Cliente();
        cliente = clienteRepository.save(cliente);
        clienteId = cliente.getId();

        TipoProduto tipoProduto = new TipoProduto();
        tipoProduto.setNome("CategoriaTeste");
        tipoProduto = tipoProdutoRepository.save(tipoProduto);

        Produto p1 = new Produto();
        p1.setNome("Produto1");
        p1.setPreco(new BigDecimal("10.00"));
        p1.setTipoProdutoId(1L);
        p1 = produtoRepository.save(p1);
        produtoId1 = p1.getId();

        Produto p2 = new Produto();
        p2.setNome("Produto2");
        p2.setPreco(new BigDecimal("20.00"));
        p1.setTipoProdutoId(2L);
        p2 = produtoRepository.save(p2);
        produtoId2 = p2.getId();

        Ingrediente ing1 = new Ingrediente();
        ing1.setNome("Ingrediente1");
        ing1.setPreco(new BigDecimal("1.00"));
        ing1 = ingredienteRepository.save(ing1);
        ingredienteId1 = ing1.getId();

        Ingrediente ing2 = new Ingrediente();
        ing2.setNome("Ingrediente2");
        ing2.setPreco(new BigDecimal("2.00"));
        ing2 = ingredienteRepository.save(ing2);
        ingredienteId2 = ing2.getId();

        Ingrediente ing3 = new Ingrediente();
        ing3.setNome("Ingrediente3");
        ing3.setPreco(new BigDecimal("3.00"));
        ing3 = ingredienteRepository.save(ing3);
        ingredienteId3 = ing3.getId();

        Ingrediente ing4 = new Ingrediente();
        ing4.setNome("Ingrediente4");
        ing4.setPreco(new BigDecimal("4.00"));
        ing4 = ingredienteRepository.save(ing4);
        ingredienteId4 = ing4.getId();
    }

    @Test
    @DisplayName("POST /api/pedidos → Criar pedido retorna 201")
    void testCriarPedido() throws Exception {
        PedidoDTO dto = new PedidoDTO();
        dto.setClienteId(clienteId);

        ItemPedidoDTO item1 = new ItemPedidoDTO();
        item1.setProdutoId(produtoId1);
        item1.setQuantidade(2);
        CustomizacaoDTO c1 = new CustomizacaoDTO();
        c1.setIngredienteId(ingredienteId1);
        c1.setTipo("addition");
        CustomizacaoDTO c2 = new CustomizacaoDTO();
        c2.setIngredienteId(ingredienteId2);
        c2.setTipo("removal");
        item1.setCustomizacoes(List.of(c1, c2));

        ItemPedidoDTO item2 = new ItemPedidoDTO();
        item2.setProdutoId(produtoId2);
        item2.setQuantidade(1);
        CustomizacaoDTO c3 = new CustomizacaoDTO();
        c3.setIngredienteId(ingredienteId3);
        c3.setTipo("addition");
        CustomizacaoDTO c4 = new CustomizacaoDTO();
        c4.setIngredienteId(ingredienteId4);
        c4.setTipo("removal");
        item2.setCustomizacoes(List.of(c3, c4));

        dto.setProdutos(List.of(item1, item2));

        mockMvc.perform(post("/api/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("GET /api/pedidos → Após criar, listar deve retornar um pedido")
    void testListarPedidos() throws Exception {
        // Cria um pedido primeiro
        PedidoDTO dto = new PedidoDTO();
        dto.setClienteId(clienteId);

        ItemPedidoDTO item = new ItemPedidoDTO();
        item.setProdutoId(produtoId1);
        item.setQuantidade(1);
        item.setCustomizacoes(List.of());
        dto.setProdutos(List.of(item));

        mockMvc.perform(post("/api/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());

        String resp = mockMvc.perform(get("/api/pedidos")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        List<PedidoDTO> lista = objectMapper.readValue(resp, new TypeReference<>() {});
        assertThat(lista).hasSize(1);
        PedidoDTO retornado = lista.get(0);
        assertThat(retornado.getClienteId()).isEqualTo(clienteId);
        assertThat(retornado.getProdutos()).hasSize(1);
        assertThat(retornado.getProdutos().get(0).getProdutoId()).isEqualTo(produtoId1);
    }

    @Test
    @DisplayName("GET /api/pedidos/{id} → Buscar por ID existente retorna 200 e objeto")
    void testBuscarPorId() throws Exception {
        // Cria um pedido
        PedidoDTO dto = new PedidoDTO();
        dto.setClienteId(clienteId);

        ItemPedidoDTO item = new ItemPedidoDTO();
        item.setProdutoId(produtoId1);
        item.setQuantidade(1);
        item.setCustomizacoes(List.of());
        dto.setProdutos(List.of(item));

        mockMvc.perform(post("/api/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());

        // Recupera ID via GET all
        String respList = mockMvc.perform(get("/api/pedidos")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        List<PedidoDTO> lista = objectMapper.readValue(respList, new TypeReference<>() {});
        Long id = lista.get(0).getId();

        mockMvc.perform(get("/api/pedidos/{id}", id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.clienteId").value(clienteId));
    }

    @Test
    @DisplayName("PUT /api/pedidos/{id}/status → Atualizar status retorna 200 e objeto atualizado")
    void testAlterarStatus() throws Exception {
        // Cria um pedido
        PedidoDTO dto = new PedidoDTO();
        dto.setClienteId(clienteId);

        ItemPedidoDTO item = new ItemPedidoDTO();
        item.setProdutoId(produtoId1);
        item.setQuantidade(1);
        item.setCustomizacoes(List.of());
        dto.setProdutos(List.of(item));

        mockMvc.perform(post("/api/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());

        // Recupera ID via GET all
        String respList = mockMvc.perform(get("/api/pedidos")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        List<PedidoDTO> lista = objectMapper.readValue(respList, new TypeReference<>() {});
        Long id = lista.get(0).getId();

        // Atualiza status para "ENTREGUE"
        String statusJson = "{\"status\":\"ENTREGUE\"}";
        mockMvc.perform(put("/api/pedidos/{id}/status", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(statusJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.status").value("ENTREGUE"));
    }

}