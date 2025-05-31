package com.checkinone.api.controller;

import com.checkinone.api.dto.HospedeDTO;
import com.checkinone.service.HospedeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/hospedes")
public class HospedeController {

    @Autowired
    private HospedeService hospedeService;

    @PostMapping
    public ResponseEntity<HospedeDTO> cadastrar(@Valid @RequestBody HospedeDTO hospede) {
        hospede = hospedeService.cadastrar(hospede);
        return ResponseEntity.status(HttpStatus.CREATED).body(hospede);
    }

    @GetMapping
    public List<HospedeDTO> listar(){
        return hospedeService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HospedeDTO> buscarPorId(@PathVariable Long id) {
        Optional<HospedeDTO> hospede = hospedeService.buscar(id);
        return hospede.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<HospedeDTO> atualizar(@PathVariable Long id, @Valid @RequestBody HospedeDTO hospede) {
        hospede.setId(id);
        hospede = hospedeService.atualizar(hospede);
        return ResponseEntity.ok(hospede);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        hospedeService.remover(id);
    }
}
