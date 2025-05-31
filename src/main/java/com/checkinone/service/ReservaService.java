package com.checkinone.service;

import com.checkinone.api.dto.HospedeDTO;
import com.checkinone.api.dto.ReservaDTO;
import com.checkinone.api.mapper.Mapper;
import com.checkinone.model.Reserva;
import com.checkinone.repository.ReservaRepository;
import com.checkinone.service.exception.NegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private Mapper mapper;

    @Autowired
    private MessageSource messageSource;


    public ReservaDTO cadastrar(ReservaDTO reservaDTO){
        Reserva reserva = mapper.map(reservaDTO, Reserva.class);
        return mapper.map(reservaRepository.save(reserva), ReservaDTO.class);
    }

    public ReservaDTO atualizar(ReservaDTO reservaDTO){
        Optional<Reserva> reservaCadastrada = reservaRepository.findById(reservaDTO.getId());
        if(reservaCadastrada.isPresent()){
            Reserva reserva = mapper.map(reservaDTO, Reserva.class);
            return mapper.map(reservaRepository.save(reserva), ReservaDTO.class);
        } else {
            throw new NegocioException(messageSource.getMessage("hotel.nao-encontrado", null, LocaleContextHolder.getLocale()));
        }

    }

    public List<ReservaDTO> listar(){
        List<Reserva> reservas = reservaRepository.findAll();
        return mapper.mapList(reservas, ReservaDTO.class);
    }

    public ReservaDTO buscarPorHospede(HospedeDTO hospedeDTO){
        //TODO
        return null;
    }

    public ReservaDTO buscarPorStatus(String statusReserva){
        //TODO
        return null;
    }

    public void remover(Long id){
        reservaRepository.deleteById(id);
    }


}
