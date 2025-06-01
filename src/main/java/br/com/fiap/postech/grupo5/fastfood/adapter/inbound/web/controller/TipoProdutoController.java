package br.com.fiap.postech.grupo5.fastfood.adapter.inbound.web.controller;

import br.com.fiap.postech.grupo5.fastfood.application.dto.TipoProdutoDTO;
import br.com.fiap.postech.grupo5.fastfood.application.service.TipoProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos/tipo")
@RequiredArgsConstructor
public class TipoProdutoController {

    private final TipoProdutoService service;

    @Operation(summary = "Criar novo tipo de produto")
    @PostMapping
    public ResponseEntity<TipoProdutoDTO> criar(@RequestBody TipoProdutoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(dto));
    }

    @Operation(summary = "Listar tipos de produtos")
    @GetMapping
    public ResponseEntity<List<TipoProdutoDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @Operation(summary = "Buscar tipo de produto por ID")
    @GetMapping("/{id}")
    public ResponseEntity<TipoProdutoDTO> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @Operation(summary = "Atualizar tipo de produto")
    @PutMapping("/{id}")
    public ResponseEntity<TipoProdutoDTO> atualizar(@PathVariable Long id, @RequestBody TipoProdutoDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @Operation(summary = "Deletar tipo de produto")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}