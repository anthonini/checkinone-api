package com.checkinone.api.mapper;

import java.util.List;

public interface Mapper {

	public <D> D map(Object fonte, Class<D> tipoDestino);
	public <S, T> List<T> mapList(List<S> fonte, Class<T> tipoDestino);
}
