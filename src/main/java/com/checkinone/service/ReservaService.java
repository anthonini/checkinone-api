package com.checkinone.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.checkinone.api.dto.HospedeDTO;
import com.checkinone.api.dto.PagamentoDTO;
import com.checkinone.api.dto.ReservaDTO;
import com.checkinone.api.mapper.Mapper;
import com.checkinone.model.Reserva;
import com.checkinone.repository.ReservaRepository;
import com.checkinone.service.exception.NegocioException;

import jakarta.transaction.Transactional;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private Mapper mapper;

    @Autowired
    private MessageSource messageSource;
    
    @Autowired
    private PagamentoService pagamentoService;

    @Transactional
    public ReservaDTO cadastrar(ReservaDTO reservaDTO){
        PagamentoDTO pagamento = pagamentoService.salvar(reservaDTO.getPagamento());
        reservaDTO.setPagamento(pagamento);
        Reserva reserva = mapper.map(reservaDTO, Reserva.class);
        reserva.setDataCadastro(new Date());
        return mapper.map(reservaRepository.save(reserva), ReservaDTO.class);
    }

    @Transactional
    public ReservaDTO atualizar(ReservaDTO reservaDTO){
        Optional<Reserva> reservaCadastrada = reservaRepository.findById(reservaDTO.getId());
        if(reservaCadastrada.isPresent()){
            return cadastrar(reservaDTO);
        } else {
            throw new NegocioException(messageSource.getMessage("reserva.nao-encontrada", null, LocaleContextHolder.getLocale()));
        }

    }

    public List<ReservaDTO> listar(){
        List<Reserva> reservas = reservaRepository.findAll();
        return mapper.mapList(reservas, ReservaDTO.class);
    }

    public Long reservasAtivasHoje() {
        return reservaRepository.countReservasAtivasHoje(LocalDateTime.now());
    }

    @Transactional
    public void remover(Long id){
        reservaRepository.deleteById(id);
    }

    public Optional<ReservaDTO> buscar(Long id) {
        return reservaRepository.findById(id).map(reserva -> mapper.map(reserva, ReservaDTO.class));
    }

    public List<ReservaDTO> listarUltimasReservas() {
        List<Reserva> reservas = reservaRepository.findTop10ByOrderByDataCadastroDesc();
        return mapper.mapList(reservas, ReservaDTO.class);
    }

    public List<ReservaDTO> listarHistoricoPorHospede(Long idHospede) {
        List<Reserva> reservas = reservaRepository.findByHospedeIdOrderByDataCadastroDesc(idHospede);
        return mapper.mapList(reservas, ReservaDTO.class);
    }

    public Long totalReservas() {
        return reservaRepository.count();
    }
}
