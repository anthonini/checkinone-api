package com.checkinone.service;

import com.checkinone.api.dto.HospedeDTO;
import com.checkinone.api.mapper.Mapper;
import com.checkinone.model.Hospede;
import com.checkinone.repository.HospedeRepository;
import com.checkinone.service.exception.NegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HospedeService {

    @Autowired
    private HospedeRepository hospedeRepository;

    @Autowired
    private Mapper mapper;
    @Qualifier("messageSource")
    @Autowired
    private MessageSource messageSource;

    public HospedeDTO cadastrar(HospedeDTO hospedeDTO) {
        Hospede hospede = mapper.map(hospedeDTO, Hospede.class);
        return mapper.map(hospedeRepository.save(hospede), HospedeDTO.class);
    }

    public HospedeDTO atualizar(HospedeDTO hospedeDTO) {
        Optional<Hospede> hospedeCadastrado = hospedeRepository.findById(hospedeDTO.getId());
        if(hospedeCadastrado.isPresent()) {
            Hospede hospede = mapper.map(hospedeDTO, Hospede.class);
            return mapper.map(hospedeRepository.save(hospede), HospedeDTO.class);
        } else {
            throw new NegocioException(messageSource.getMessage("hospede.nao-encontrado", null, LocaleContextHolder.getLocale()));
        }
    }

    public HospedeDTO buscarPorNome(String nome) {
        //TODO
        return null;
    }

    public List<HospedeDTO> listar() {
        List<Hospede> hospedes = hospedeRepository.findAll();
        return mapper.mapList(hospedes, HospedeDTO.class);
    }

    public void remover(Long id) {
        hospedeRepository.deleteById(id);
    }

}
