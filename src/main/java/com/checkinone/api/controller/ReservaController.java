package com.checkinone.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.checkinone.api.dto.ReservaDTO;
import com.checkinone.service.ReservaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/reservas")
public class ReservaController {
    
    @Autowired
    private ReservaService reservaService;

    @PostMapping
    public ResponseEntity<ReservaDTO> cadastrar(@Valid @RequestBody ReservaDTO reserva) {
        reserva = reservaService.cadastrar(reserva);
        return ResponseEntity.status(HttpStatus.CREATED).body(reserva);
    }

    @GetMapping
    public List<ReservaDTO> listar() {
        return reservaService.listar();
    }



    @GetMapping("/{id}")
    public ResponseEntity<ReservaDTO> buscarPorId(@PathVariable Long id) {
        Optional<ReservaDTO> reserva = reservaService.buscar(id);
        return reserva.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservaDTO> atualizar(@PathVariable Long id, @Valid @RequestBody ReservaDTO reserva) {
        reserva.setId(id);
        reserva = reservaService.atualizar(reserva);
        return ResponseEntity.ok(reserva);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        reservaService.remover(id);
    }

    @GetMapping("/ultimas")
    public List<ReservaDTO> ultimasReservas() {
        return reservaService.listarUltimasReservas();
    }

    @GetMapping("/historico/{idHospede}")
    public List<ReservaDTO> historicoPorHospede(@PathVariable Long idHospede) {
        return reservaService.listarHistoricoPorHospede(idHospede);
    }
}
