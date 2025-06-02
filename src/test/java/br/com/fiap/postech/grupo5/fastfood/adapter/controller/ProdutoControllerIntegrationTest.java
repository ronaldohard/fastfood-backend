package br.com.fiap.postech.grupo5.fastfood.adapter.controller;

import br.com.fiap.postech.grupo5.fastfood.adapter.outbound.repositories.ProdutoRepository;
import br.com.fiap.postech.grupo5.fastfood.application.dto.ProdutoDTO;
import br.com.fiap.postech.grupo5.fastfood.application.service.ProdutoService;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchIllegalArgumentException;
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
class ProdutoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoService produtoService;

    @BeforeEach
    void setup() throws Exception {
        produtoRepository.deleteAll();
    }

    @Test
    @DisplayName("POST /api/produtos → Criar um produto deve retornar 201 e o objeto criado")
    void testCriarProduto() throws Exception {
        ProdutoDTO dto = new ProdutoDTO();
        dto.setTipoProdutoId(Long.valueOf(1));
        dto.setNome("Hambúrguer de Carne");
        dto.setPreco(new BigDecimal("19.90"));

        mockMvc.perform(post("/api/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nome").value("Hambúrguer de Carne"))
                .andExpect(jsonPath("$.preco").value(BigDecimal.valueOf(19.90)))
                .andExpect(jsonPath("$.tipoProdutoId").value(1));
    }

    @Test
    @DisplayName("GET /api/produtos → Após criar produtos, deve listar todos")
    void testListarComDados() throws Exception {
        ProdutoDTO p1 = new ProdutoDTO();
        p1.setTipoProdutoId(Long.valueOf(1));
        p1.setNome("x-burguer");
        p1.setPreco(new BigDecimal("29.50"));

        ProdutoDTO p2 = new ProdutoDTO();
        p2.setTipoProdutoId(Long.valueOf(3));
        p2.setNome("Refrigerante 600ml");
        p2.setPreco(new BigDecimal("7.00"));

        mockMvc.perform(post("/api/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(p1)))
                .andExpect(status().isCreated());

        mockMvc.perform(post("/api/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(p2)))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/api/produtos")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].nome").value("x-burguer"))
                .andExpect(jsonPath("$[1].nome").value("Refrigerante 600ml"));
    }

    @Test
    @DisplayName("GET /api/produtos/{id} → Buscar por ID existente retorna 200 e o objeto")
    void testBuscarPorIdExistente() throws Exception {
        ProdutoDTO dto = new ProdutoDTO();
        dto.setTipoProdutoId(Long.valueOf(2));
        dto.setNome("Batata Frita");
        dto.setPreco(new BigDecimal("12.00"));

        String response = mockMvc.perform(post("/api/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        ProdutoDTO criado = objectMapper.readValue(response, ProdutoDTO.class);
        String nome = criado.getNome();
        assertThat(nome).isNotNull();
        Long id = criado.getId();
        mockMvc.perform(get("/api/produtos/{id}", id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Batata Frita"))
                .andExpect(jsonPath("$.preco").value(12.00));
    }

    @Test
    @DisplayName("PUT /api/produtos/{id} → Atualizar produto existente retorna 200 e objeto atualizado")
    void testAtualizarProduto() throws Exception {
        ProdutoDTO dto = new ProdutoDTO();
        dto.setTipoProdutoId(Long.valueOf(1));
        dto.setNome("Suco Natural");
        dto.setPreco(new BigDecimal("8.50"));

        String response = mockMvc.perform(post("/api/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        ProdutoDTO criado = objectMapper.readValue(response, ProdutoDTO.class);
        Long id = criado.getId();

        ProdutoDTO dtoAlterado = new ProdutoDTO();
        dtoAlterado.setNome("Suco Natural de Laranja");
        dtoAlterado.setPreco(new BigDecimal("9.00"));
        dtoAlterado.setTipoProdutoId(Long.valueOf(1));

        mockMvc.perform(put("/api/produtos/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dtoAlterado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Suco Natural de Laranja"))
                .andExpect(jsonPath("$.preco").value(9.00));
    }

    @Test
    @DisplayName("DELETE /api/produtos/{id} → Deletar produto existente retorna 204")
    void testDeletarProduto() throws Exception {
        ProdutoDTO dto = new ProdutoDTO();
        dto.setNome("Açai");
        dto.setPreco(new BigDecimal("15.00"));

        mockMvc.perform(post("/api/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Long id = Long.valueOf(1);

        mockMvc.perform(delete("/api/produtos/{id}", id))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/produtos/{id}", id))
                .andExpect(status().isBadRequest());
    }
}
