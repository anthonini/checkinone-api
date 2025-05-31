package com.checkinone.service;

import java.util.List;
import java.util.Optional;

import com.checkinone.model.StatusOcupacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.checkinone.api.dto.QuartoDTO;
import com.checkinone.api.mapper.Mapper;
import com.checkinone.model.Quarto;
import com.checkinone.repository.QuartoRepository;
import com.checkinone.service.exception.NegocioException;

import jakarta.transaction.Transactional;

@Service
public class QuartoService {

    @Autowired
    private QuartoRepository quartoRepository;

    @Autowired
    private Mapper mapper;

    @Autowired
    private MessageSource messageSource;


    @Transactional
    public QuartoDTO cadastrar(QuartoDTO quartoDTO){
        Quarto quarto = mapper.map(quartoDTO, Quarto.class);
        return mapper.map(quartoRepository.save(quarto), QuartoDTO.class);
    }

    @Transactional
    public QuartoDTO atualizar(QuartoDTO quartoDTO){
        Optional<Quarto> quartoCadastrado = quartoRepository.findById(quartoDTO.getId());
        if(quartoCadastrado.isPresent()) {
            Quarto quarto = mapper.map(quartoDTO, Quarto.class);
            return mapper.map(quartoRepository.save(quarto), QuartoDTO.class);
        } else {
            throw new NegocioException(messageSource.getMessage("quarto.nao-encontrado", null, LocaleContextHolder.getLocale()));
        }
    }

    public List<QuartoDTO> listar() {
        List<Quarto> quartos = quartoRepository.findAll();
        return mapper.mapList(quartos, QuartoDTO.class);
    }

    @Transactional
    public void remover(Long id) {
        quartoRepository.deleteById(id);
    }


    public Optional<QuartoDTO> buscar(Long id) {
        return quartoRepository.findById(id).map(quarto -> mapper.map(quarto, QuartoDTO.class));
    }

    public Long totalQuartos() {
        return quartoRepository.count();
    }

    public Long totalQuartosOcupados() {
        return quartoRepository.countByStatus(StatusOcupacao.OCUPADO);
    }
}
