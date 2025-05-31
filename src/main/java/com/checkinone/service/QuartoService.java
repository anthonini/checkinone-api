package com.checkinone.service;

import com.checkinone.api.dto.QuartoDTO;
import com.checkinone.api.mapper.Mapper;
import com.checkinone.model.Quarto;
import com.checkinone.repository.QuartoRepository;
import com.checkinone.service.exception.NegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuartoService {

    @Autowired
    private QuartoRepository quartoRepository;

    @Autowired
    private Mapper mapper;

    @Autowired
    private MessageSource messageSource;


    public QuartoDTO cadastrar(QuartoDTO quartoDTO){
        Quarto quarto = mapper.map(quartoDTO, Quarto.class);
        return mapper.map(quartoRepository.save(quarto), QuartoDTO.class);
    }

    public QuartoDTO atualizar(QuartoDTO quartoDTO){
        Optional<Quarto> quartoCadastrado = quartoRepository.findById(quartoDTO.getId());
        if(quartoCadastrado.isPresent()) {
            Quarto quarto = mapper.map(quartoDTO, Quarto.class);
            return mapper.map(quartoRepository.save(quarto), QuartoDTO.class);
        } else {
            throw new NegocioException(messageSource.getMessage("hospede.nao-encontrado", null, LocaleContextHolder.getLocale()));
        }
    }

    public QuartoDTO buscarPorNumeroQuarto(int numero) {
        //TODO
        return null;
    }

    public List<QuartoDTO> listar() {
        List<Quarto> quartos = quartoRepository.findAll();
        return mapper.mapList(quartos, QuartoDTO.class);
    }

    public void remover(Long id) {
        quartoRepository.deleteById(id);
    }


}
