package com.checkinone.service;

import com.checkinone.api.dto.HospedeDTO;
import com.checkinone.api.mapper.Mapper;
import com.checkinone.repository.HospedeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospedeService {

    @Autowired
    private HospedeRepository hospedeRepository;

    @Autowired
    private Mapper mapper;

    public HospedeDTO cadastrar(HospedeDTO hospedeDTO) {
        //TODO
        return null;
    }

    public HospedeDTO atualizar(HospedeDTO hospedeDTO) {
        //TODO
        return null;
    }

    public HospedeDTO buscarPorNome(String nome) {
        //TODO
        return null;
    }

    public List<HospedeDTO> listar() {
        //TODO
        return null;
    }

    public void remover(Long id) {
        //TODO
    }


}
