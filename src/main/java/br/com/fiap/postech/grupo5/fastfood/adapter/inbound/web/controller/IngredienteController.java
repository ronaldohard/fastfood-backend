package br.com.fiap.postech.grupo5.fastfood.adapter.inbound.web.controller;

import br.com.fiap.postech.grupo5.fastfood.application.dto.IngredienteDTO;
import br.com.fiap.postech.grupo5.fastfood.application.service.IngredienteService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ingredientes")
@RequiredArgsConstructor
public class IngredienteController {

    private final IngredienteService service;

    @Operation(summary = "Criar novo ingrediente")
    @PostMapping
    public ResponseEntity<IngredienteDTO> criar(@RequestBody IngredienteDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(dto));
    }

    @Operation(summary = "Listar ingredientes")
    @GetMapping
    public ResponseEntity<List<IngredienteDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @Operation(summary = "Buscar ingrediente por ID")
    @GetMapping("/{id}")
    public ResponseEntity<IngredienteDTO> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @Operation(summary = "Atualizar ingrediente")
    @PutMapping("/{id}")
    public ResponseEntity<IngredienteDTO> atualizar(@PathVariable Long id, @RequestBody IngredienteDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @Operation(summary = "Deletar ingrediente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}