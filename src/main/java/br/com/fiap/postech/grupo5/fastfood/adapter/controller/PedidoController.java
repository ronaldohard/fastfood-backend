package br.com.fiap.postech.grupo5.fastfood.adapter.controller;

import br.com.fiap.postech.grupo5.fastfood.dto.PedidoDTO;
import br.com.fiap.postech.grupo5.fastfood.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
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
}
