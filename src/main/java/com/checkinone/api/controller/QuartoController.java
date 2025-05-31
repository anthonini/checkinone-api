package com.checkinone.api.controller;

import com.checkinone.api.dto.QuartoDTO;
import com.checkinone.service.QuartoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/quartos")
public class QuartoController {

    @Autowired
    private QuartoService quartoService;

    @PostMapping
    public ResponseEntity<QuartoDTO> cadastrar(@Valid @RequestBody QuartoDTO quarto) {
        quarto = quartoService.cadastrar(quarto);
        return ResponseEntity.status(HttpStatus.CREATED).body(quarto);
    }

    @GetMapping
    public List<QuartoDTO> listar() {
        return quartoService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuartoDTO> buscarPorId(@PathVariable Long id) {
        Optional<QuartoDTO> quarto = quartoService.buscar(id);
        return quarto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuartoDTO> atualizar(@PathVariable Long id, @Valid @RequestBody QuartoDTO quarto) {
        quarto.setId(id);
        quarto = quartoService.atualizar(quarto);
        return ResponseEntity.ok(quarto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        quartoService.remover(id);
    }
}
