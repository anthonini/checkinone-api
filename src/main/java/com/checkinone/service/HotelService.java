package com.checkinone.service;

import com.checkinone.api.dto.HotelDTO;
import com.checkinone.api.mapper.Mapper;
import com.checkinone.model.Hotel;
import com.checkinone.repository.HotelRepository;
import com.checkinone.service.exception.NegocioException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private Mapper mapper;

    @Autowired
    private MessageSource messageSource;

    @Transactional
    public HotelDTO cadastrar(HotelDTO hotelDTO) {
        Hotel hotel = mapper.map(hotelDTO, Hotel.class);
        return mapper.map(hotelRepository.save(hotel), HotelDTO.class);
    }

    public HotelDTO atualizar(@Valid HotelDTO hotelDTO) {
        Optional<Hotel> hotelCadastrado = hotelRepository.findById(hotelDTO.getId());
        if(hotelCadastrado.isPresent()) {
            Hotel hotel = mapper.map(hotelDTO, Hotel.class);
            return mapper.map(hotelRepository.save(hotel), HotelDTO.class);
        } else{
            throw new NegocioException(messageSource.getMessage("hotel.nao-encontrado", null, LocaleContextHolder.getLocale()));
        }

    }

    public Optional<HotelDTO> buscar(Long id) {
        return hotelRepository.findById(id).map(hotel -> mapper.map(hotel, HotelDTO.class));
    }

    public void remover(Long id) {
        hotelRepository.deleteById(id);
    }
    
    public List<HotelDTO> listarTodos(){
        List<Hotel> hoteis = hotelRepository.findAll();
        return mapper.mapList(hoteis, HotelDTO.class);
    }

}
