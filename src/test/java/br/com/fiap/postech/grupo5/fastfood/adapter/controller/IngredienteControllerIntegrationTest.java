package br.com.fiap.postech.grupo5.fastfood.adapter.controller;

import br.com.fiap.postech.grupo5.fastfood.adapter.outbound.entity.ingredient.Ingrediente;
import br.com.fiap.postech.grupo5.fastfood.adapter.outbound.repositories.IngredienteRepository;
import br.com.fiap.postech.grupo5.fastfood.application.dto.IngredienteDTO;
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

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class IngredienteControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private IngredienteRepository ingredienteRepository;

    @BeforeEach
    void setup() {
        ingredienteRepository.deleteAll();
    }

    @Test
    @DisplayName("POST /api/ingredientes → Criar novo ingrediente retorna 201 e o objeto criado")
    void testCriarIngrediente() throws Exception {
        IngredienteDTO dto = new IngredienteDTO();
        dto.setNome("Tomate");
        dto.setPreco(new BigDecimal("2.50"));

        mockMvc.perform(post("/api/ingredientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.nome").value("Tomate"))
                .andExpect(jsonPath("$.preco").value(2.50));
    }

    @Test
    @DisplayName("GET /api/ingredientes → Quando não há ingredientes, retorna lista vazia")
    void testListarVazio() throws Exception {
        mockMvc.perform(get("/api/ingredientes")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    @DisplayName("GET /api/ingredientes → Após criar ingredientes, deve listar todos")
    void testListarComDados() throws Exception {
        Ingrediente i1 = new Ingrediente();
        i1.setNome("Alface");
        i1.setPreco(new BigDecimal("1.00"));
        ingredienteRepository.save(i1);

        Ingrediente i2 = new Ingrediente();
        i2.setNome("Queijo");
        i2.setPreco(new BigDecimal("3.00"));
        ingredienteRepository.save(i2);

        mockMvc.perform(get("/api/ingredientes")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].nome").value("Alface"))
                .andExpect(jsonPath("$[0].preco").value(1.00))
                .andExpect(jsonPath("$[1].nome").value("Queijo"))
                .andExpect(jsonPath("$[1].preco").value(3.00));
    }

    @Test
    @DisplayName("GET /api/ingredientes/{id} → Buscar por ID existente retorna 200 e o objeto")
    void testBuscarPorIdExistente() throws Exception {
        Ingrediente ing = new Ingrediente();
        ing.setNome("Cebola");
        ing.setPreco(new BigDecimal("1.50"));
        Ingrediente salvo = ingredienteRepository.save(ing);
        Long id = salvo.getId();

        mockMvc.perform(get("/api/ingredientes/{id}", id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.nome").value("Cebola"))
                .andExpect(jsonPath("$.preco").value(1.50));
    }

    @Test
    @DisplayName("PUT /api/ingredientes/{id} → Atualizar ingrediente existente retorna 200 e objeto atualizado")
    void testAtualizarIngrediente() throws Exception {
        Ingrediente ing = new Ingrediente();
        ing.setNome("Pimenta");
        ing.setPreco(new BigDecimal("0.80"));
        Ingrediente salvo = ingredienteRepository.save(ing);
        Long id = salvo.getId();

        IngredienteDTO dtoAlterado = new IngredienteDTO();
        dtoAlterado.setNome("Pimenta Doce");
        dtoAlterado.setPreco(new BigDecimal("1.00"));

        mockMvc.perform(put("/api/ingredientes/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dtoAlterado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.nome").value("Pimenta Doce"))
                .andExpect(jsonPath("$.preco").value(1.00));

        Ingrediente atualizado = ingredienteRepository.findById(id).orElseThrow();
        assertThat(atualizado.getNome()).isEqualTo("Pimenta Doce");
        assertThat(atualizado.getPreco()).isEqualByComparingTo(new BigDecimal("1.00"));
    }

    @Test
    @DisplayName("DELETE /api/ingredientes/{id} → Deletar ingrediente existente retorna 204")
    void testDeletarIngrediente() throws Exception {
        Ingrediente ing = new Ingrediente();
        ing.setNome("Azeitona");
        ing.setPreco(new BigDecimal("2.20"));
        Ingrediente salvo = ingredienteRepository.save(ing);
        Long id = salvo.getId();

        mockMvc.perform(delete("/api/ingredientes/{id}", id))
                .andExpect(status().isNoContent());

        List<Ingrediente> todos = ingredienteRepository.findAll();
        assertThat(todos).isEmpty();
    }
}
