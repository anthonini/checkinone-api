package com.checkinone.service;

import com.checkinone.api.dto.HotelDTO;
import com.checkinone.api.mapper.Mapper;
import com.checkinone.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private Mapper mapper;

    public HotelDTO cadastrar(HotelDTO hospedeDTO) {
        //TODO
        return null;
    }

    public HotelDTO atualizar(HotelDTO hospedeDTO) {
        //TODO
        return null;
    }

    public void remover(Long id) {
        //TODO
    }
    
    public List<HotelDTO> listarTodos(){
        //TODO
        return null;
    }

}
