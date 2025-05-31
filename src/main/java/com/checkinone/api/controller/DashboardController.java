package com.checkinone.api.controller;

import com.checkinone.service.QuartoService;
import com.checkinone.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private QuartoService quartoService;

    @GetMapping("/ativas-hoje")
    public ResponseEntity<Long> reservasAtivasHoje(){
        Long total = reservaService.reservasAtivasHoje();
        return ResponseEntity.ok(total);
    }

    @GetMapping("/total-reservas")
    public ResponseEntity<Long> totaldeReservas(){
        Long total = reservaService.totalReservas();
        return ResponseEntity.ok(total);
    }

    @GetMapping("/total-quartos")
    public ResponseEntity<Long> totaldeQuartos(){
        Long total = quartoService.totalQuartos();
        return ResponseEntity.ok(total);
    }

    @GetMapping("/total-quartos-ocupados")
    public ResponseEntity<Long> totalQuartosOcupados() {
        Long total = quartoService.totalQuartosOcupados();
        return ResponseEntity.ok(total);
    }

}
