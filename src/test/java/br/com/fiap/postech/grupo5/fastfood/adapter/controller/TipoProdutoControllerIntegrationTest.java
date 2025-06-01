package br.com.fiap.postech.grupo5.fastfood.adapter.controller;

import br.com.fiap.postech.grupo5.fastfood.adapter.outbound.entity.product.TipoProduto;
import br.com.fiap.postech.grupo5.fastfood.adapter.outbound.repositories.TipoProdutoRepository;
import br.com.fiap.postech.grupo5.fastfood.application.dto.TipoProdutoDTO;
import br.com.fiap.postech.grupo5.fastfood.application.service.TipoProdutoService;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class TipoProdutoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TipoProdutoRepository tipoProdutoRepository;

    @Autowired
    private TipoProdutoService tipoProdutoService;

    @BeforeEach
    void setup() throws Exception {
        tipoProdutoRepository.deleteAll();
    }

    @Test
    @DisplayName("POST /api/produtos/tipo → Criar novo tipo de produto retorna 201 e o objeto criado")
    void testCriarTipoProduto() throws Exception {
        TipoProdutoDTO dto = new TipoProdutoDTO();
        dto.setNome("Bebida");

        mockMvc.perform(post("/api/produtos/tipo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.nome").value("Bebida"));
    }

    @Test
    @DisplayName("GET /api/produtos/tipo → Quando não há tipos, retorna lista vazia")
    void testListarVazio() throws Exception {
        mockMvc.perform(get("/api/produtos/tipo")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    @DisplayName("GET /api/produtos/tipo → Após criar tipos, deve listar todos")
    void testListarComDados() throws Exception {
        TipoProduto t1 = new TipoProduto();
        t1.setNome("Sanduíche");
        tipoProdutoRepository.save(t1);

        TipoProduto t2 = new TipoProduto();
        t2.setNome("Sobremesa");
        tipoProdutoRepository.save(t2);

        mockMvc.perform(get("/api/produtos/tipo")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].nome").value("Sanduíche"))
                .andExpect(jsonPath("$[1].nome").value("Sobremesa"));
    }

    @Test
    @DisplayName("GET /api/produtos/tipo/{id} → Buscar por ID existente retorna 200 e o objeto")
    void testBuscarPorIdExistente() throws Exception {
        // Primeiro salva um tipo
        TipoProduto t = new TipoProduto();
        t.setNome("Lanche");
        TipoProduto salvo = tipoProdutoRepository.save(t);
        Long id = salvo.getId();

        mockMvc.perform(get("/api/produtos/tipo/{id}", id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.nome").value("Lanche"));
    }

    @Test
    @DisplayName("PUT /api/produtos/tipo/{id} → Atualizar tipo existente retorna 200 e objeto atualizado")
    void testAtualizarTipoProduto() throws Exception {
        TipoProduto t = new TipoProduto();
        t.setNome("Bebida Quente");
        TipoProduto salvo = tipoProdutoRepository.save(t);
        Long id = salvo.getId();

        TipoProdutoDTO dtoAlterado = new TipoProdutoDTO();
        dtoAlterado.setNome("Bebida Gelada");

        mockMvc.perform(put("/api/produtos/tipo/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dtoAlterado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.nome").value("Bebida Gelada"));

        // Verifica diretamente no repositório
        TipoProduto atualizado = tipoProdutoRepository.findById(id).orElseThrow();
        assertThat(atualizado.getNome()).isEqualTo("Bebida Gelada");
    }

    @Test
    @DisplayName("DELETE /api/produtos/tipo/{id} → Deletar tipo existente retorna 204")
    void testDeletarTipoProduto() throws Exception {
        TipoProduto t = new TipoProduto();
        t.setNome("Porção");
        TipoProduto salvo = tipoProdutoRepository.save(t);
        Long id = salvo.getId();

        mockMvc.perform(delete("/api/produtos/tipo/{id}", id))
                .andExpect(status().isNoContent());

        assertThat(tipoProdutoRepository.findById(id)).isEmpty();
    }

}
