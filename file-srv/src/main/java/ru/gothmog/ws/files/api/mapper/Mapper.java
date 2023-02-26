package ru.gothmog.ws.files.api.mapper;



import ru.gothmog.ws.files.api.dto.AbstractDto;
import ru.gothmog.ws.files.core.model.AbstractEntity;

import java.util.List;

public interface Mapper<E extends AbstractEntity, D extends AbstractDto> {

  E toEntity(D dto);

  D toDto(E entity);

  List<D> toArraysToDto(List<E> inArray);
}
