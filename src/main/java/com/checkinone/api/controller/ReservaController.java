package com.checkinone.api.controller;

import com.checkinone.api.dto.ReservaDTO;
import com.checkinone.api.service.PagamentoService;
import com.checkinone.service.ReservaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/reservas")
public class ReservaController {
    
    @Autowired
    private ReservaService reservaService;

    @Autowired
    private PagamentoService pagamentoService;

    @PostMapping
    public ResponseEntity<ReservaDTO> cadastrar(@Valid @RequestBody ReservaDTO reserva) {
        reserva = reservaService.cadastrar(reserva);
        //TODO definir estratégia para o pagamento

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
