package ru.gothmog.ws.auth.api.mapper;


import ru.gothmog.ws.auth.api.dto.AbstractDto;
import ru.gothmog.ws.auth.core.model.AbstractEntity;

import java.util.List;

public interface Mapper<E extends AbstractEntity, D extends AbstractDto> {

  E toEntity(D dto);

  D toDto(E entity);

  List<D> toArraysToDto(List<E> inArray);
}
