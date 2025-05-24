package com.checkinone.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MapperImpl implements Mapper {
	
	 ModelMapper modelMapper = new ModelMapper();

	@Override
	public <D> D map(Object fonte, Class<D> tipoDestino) {
		return modelMapper.map(fonte, tipoDestino);
	}

	@Override
	public <S, T> List<T> mapList(List<S> fonte, Class<T> tipoDestino) {
	    return fonte.stream()
	                 .map(elemento -> modelMapper.map(elemento, tipoDestino))
	                 .collect(Collectors.toList());
	}

}
