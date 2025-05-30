package com.checkinone.service;

import com.checkinone.api.dto.QuartoDTO;
import com.checkinone.api.mapper.Mapper;
import com.checkinone.repository.QuartoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuartoService {

    @Autowired
    private QuartoRepository quartoRepository;

    @Autowired
    private Mapper mapper;


    public QuartoDTO cadastrar(QuartoDTO quartoDTO){
        //TODO
        return null;
    }

    public QuartoDTO atualizar(QuartoDTO quartoDTO){
        //TODO
        return null;
    }

    public QuartoDTO buscarPorNumeroQuarto(int numero) {
        //TODO
        return null;
    }

    public List<QuartoDTO> listar() {
        //TODO
        return null;
    }

    public void remover(Long id) {
        //TODO
    }


}
