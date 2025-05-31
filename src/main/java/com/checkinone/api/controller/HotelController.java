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

import com.checkinone.api.dto.HotelDTO;
import com.checkinone.service.HotelService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/hoteis")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping
    public ResponseEntity<HotelDTO> cadastrar(@Valid @RequestBody HotelDTO hotel) {
        hotel = hotelService.cadastrar(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(hotel);
    }

    @GetMapping
    public List<HotelDTO> listar(){
        return hotelService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelDTO> buscarPorId(@PathVariable Long id) {
        Optional<HotelDTO> hotel = hotelService.buscar(id);
        return hotel.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<HotelDTO> atualizar(@PathVariable Long id, @Valid @RequestBody HotelDTO hotel) {
        hotel.setId(id);
        hotel = hotelService.atualizar(hotel);
        return ResponseEntity.ok(hotel);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        hotelService.remover(id);
    }
}
