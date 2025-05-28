package br.com.fiap.postech.grupo5.fastfood.adapter.inbound.controller;

import br.com.fiap.postech.grupo5.fastfood.application.dto.MonitorDTO;
import br.com.fiap.postech.grupo5.fastfood.application.dto.PedidoDTO;
import br.com.fiap.postech.grupo5.fastfood.application.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
@Tag(name = "0 - Pedido", description = "Operações relacionadas a pedidos")
public class PedidoController {

    private final PedidoService service;
    private final String string = """
              {
                "clienteId": 1,
                "produtos": [
                  {
                    "produtoId": 1,
                    "quantidade": 2,
                    "customizacoes": [
                      { "ingredienteId": 4, "tipo": "addition" },
                      { "ingredienteId": 1, "tipo": "removal" }
                    ]
                  },
                  {
                    "produtoId": 2,
                    "quantidade": 1,
                    "customizacoes": [
                      { "ingredienteId": 5, "tipo": "addition" },
                      { "ingredienteId": 2, "tipo": "removal" }
                    ]
                  }
                ]
              }
            """;

    @Operation(
            summary = "Cria um novo pedido",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PedidoDTO.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Pedido exemplo",
                                            value = string
                                    )
                            }
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pedido criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno")
    })
    @PostMapping
    public ResponseEntity<Void> criarPedido(@RequestBody PedidoDTO request) {
        service.criarPedido(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Buscar pedido por status")
    @GetMapping("/status/{status}")
    public ResponseEntity<PedidoDTO> buscar(@PathVariable String status) {
        return ResponseEntity.ok(service.buscarPorStatus(status));
    }


    @Operation(summary = "Buscar pedido por id")
    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    //todo - paginar
    @Operation(summary = "Buscar todos os pedidos")
    @GetMapping("")
    public ResponseEntity<List<PedidoDTO>> buscarTodosPedidos() {
        return ResponseEntity.ok(service.buscarTodos());
    }

    //todo - paginar
    @Operation(summary = "Listar os pedidos para o monitor")
    @GetMapping("/monitor")
    public ResponseEntity<List<MonitorDTO>> buscarPedidosMonitor() {
        return ResponseEntity.ok(service.buscarPedidosMonitor());
    }

    @Operation(summary = "Alterar Status")
    @PutMapping("/{id}/status")
    public ResponseEntity<PedidoDTO> alterarStatusPedido(
            @PathVariable Long id,
            @RequestBody AlterarStatusDTO alterarStatus) {
        return ResponseEntity.ok(service.alterarStatus(id, alterarStatus.getStatus()));
    }

    @Data
    public static class AlterarStatusDTO {
        @NotBlank
        private String status;
    }


}
