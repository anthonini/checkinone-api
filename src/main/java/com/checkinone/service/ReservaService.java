package com.checkinone.service;

import com.checkinone.api.dto.HospedeDTO;
import com.checkinone.api.dto.ReservaDTO;
import com.checkinone.api.mapper.Mapper;
import com.checkinone.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private Mapper mapper;


    public ReservaDTO cadastrar(ReservaDTO reservaDTO){
        //TODO
        return null;
    }

    public ReservaDTO atualizar(ReservaDTO reservaDTO){
        //TODO
        return null;
    }

    public List<ReservaDTO> listar(){
        //TODO
        return null;
    }

    public ReservaDTO buscarPorHospede(HospedeDTO hospedeDTO){
        //TODO
        return null;
    }

    public ReservaDTO buscarPorStatus(String statusReserva){
        //TODO
        return null;
    }


}
