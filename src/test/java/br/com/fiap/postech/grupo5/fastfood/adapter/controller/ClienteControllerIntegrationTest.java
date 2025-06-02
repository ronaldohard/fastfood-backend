package br.com.fiap.postech.grupo5.fastfood.adapter.controller;

import br.com.fiap.postech.grupo5.fastfood.adapter.outbound.entity.client.Cliente;
import br.com.fiap.postech.grupo5.fastfood.adapter.outbound.repositories.ClienteRepository;
import br.com.fiap.postech.grupo5.fastfood.application.dto.ClienteDTO;
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
class ClienteControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ClienteRepository clienteRepository;

    @BeforeEach
    void setup() {
        clienteRepository.deleteAll();
    }

    @Test
    @DisplayName("POST /api/clientes → Criar novo cliente retorna 201 e o objeto criado")
    void testCriarCliente() throws Exception {
        ClienteDTO dto = new ClienteDTO();
        dto.setNome("João Silva");
        dto.setCel("11999999999");
        dto.setCpf("123.456.789-00");

        mockMvc.perform(post("/api/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.nome").value("João Silva"))
                .andExpect(jsonPath("$.cel").value("11999999999"))
                .andExpect(jsonPath("$.cpf").value("123.456.789-00"));
    }

    @Test
    @DisplayName("GET /api/clientes → Quando não há clientes, retorna lista vazia")
    void testListarVazio() throws Exception {
        mockMvc.perform(get("/api/clientes")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    @DisplayName("GET /api/clientes → Após criar clientes, deve listar todos")
    void testListarComDados() throws Exception {
        Cliente c1 = new Cliente();
        c1.setNome("Maria Souza");
        c1.setCel("11988888888");
        c1.setCpf("111.222.333-44");
        clienteRepository.save(c1);

        Cliente c2 = new Cliente();
        c2.setNome("Pedro Santos");
        c2.setCel("11977777777");
        c2.setCpf("555.666.777-88");
        clienteRepository.save(c2);

        mockMvc.perform(get("/api/clientes")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].nome").value("Maria Souza"))
                .andExpect(jsonPath("$[0].cel").value("11988888888"))
                .andExpect(jsonPath("$[0].cpf").value("111.222.333-44"))
                .andExpect(jsonPath("$[1].nome").value("Pedro Santos"))
                .andExpect(jsonPath("$[1].cel").value("11977777777"))
                .andExpect(jsonPath("$[1].cpf").value("555.666.777-88"));
    }

    @Test
    @DisplayName("GET /api/clientes/{id} → Buscar por ID existente retorna 200 e o objeto")
    void testBuscarPorIdExistente() throws Exception {
        Cliente c = new Cliente();
        c.setNome("Ana Lima");
        c.setCel("11966666666");
        c.setCpf("999.888.777-66");
        Cliente salvo = clienteRepository.save(c);
        Long id = salvo.getId();

        mockMvc.perform(get("/api/clientes/{id}", id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.nome").value("Ana Lima"))
                .andExpect(jsonPath("$.cel").value("11966666666"))
                .andExpect(jsonPath("$.cpf").value("999.888.777-66"));
    }

    @Test
    @DisplayName("GET /api/clientes/cpf/{cpf} → Buscar por CPF existente retorna 200 e o objeto")
    void testBuscarPorCpfExistente() throws Exception {
        Cliente c = new Cliente();
        c.setNome("Bruna Alves");
        c.setCel("11955555555");
        c.setCpf("222.333.444-55");
        clienteRepository.save(c);

        mockMvc.perform(get("/api/clientes/cpf/{cpf}", "222.333.444-55")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Bruna Alves"))
                .andExpect(jsonPath("$.cel").value("11955555555"))
                .andExpect(jsonPath("$.cpf").value("222.333.444-55"));
    }

    @Test
    @DisplayName("PUT /api/clientes/{id} → Atualizar cliente existente retorna 200 e objeto atualizado")
    void testAtualizarCliente() throws Exception {
        Cliente c = new Cliente();
        c.setNome("Carlos Pereira");
        c.setCel("11944444444");
        c.setCpf("333.444.555-66");
        Cliente salvo = clienteRepository.save(c);
        Long id = salvo.getId();

        ClienteDTO dtoAlterado = new ClienteDTO();
        dtoAlterado.setId(salvo.getId());
        dtoAlterado.setNome("Carlos P. Silva");
        dtoAlterado.setCel("11900000000");
        dtoAlterado.setCpf("333.444.555-66");

        mockMvc.perform(put("/api/clientes/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dtoAlterado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.nome").value("Carlos P. Silva"))
                .andExpect(jsonPath("$.cel").value("11900000000"))
                .andExpect(jsonPath("$.cpf").value("333.444.555-66"));

        Cliente atualizado = clienteRepository.findById(id).orElseThrow();
        assertThat(atualizado.getNome()).isEqualTo("Carlos P. Silva");
        assertThat(atualizado.getCel()).isEqualTo("11900000000");
    }

    @Test
    @DisplayName("DELETE /api/clientes/{id} → Deletar cliente existente retorna 204")
    void testDeletarCliente() throws Exception {
        Cliente c = new Cliente();
        c.setNome("Fernanda Costa");
        c.setCel("11933333333");
        c.setCpf("777.888.999-00");
        Cliente salvo = clienteRepository.save(c);
        Long id = salvo.getId();

        mockMvc.perform(delete("/api/clientes/{id}", id))
                .andExpect(status().isNoContent());

        List<Cliente> todos = clienteRepository.findAll();
        assertThat(todos).isEmpty();
    }

}
