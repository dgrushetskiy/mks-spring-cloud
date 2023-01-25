package ru.gothmog.ws.auth.api.mapper;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gothmog.ws.auth.api.dto.AbstractDto;
import ru.gothmog.ws.auth.core.model.AbstractEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractMapper<E extends AbstractEntity, D extends AbstractDto> implements
    Mapper<E, D> {

  private final Class<E> entityClass;
  private final Class<D> dtoClass;

  private ModelMapper mapper;

  protected AbstractMapper(Class<E> entityClass, Class<D> dtoClass) {
    this.entityClass = entityClass;
    this.dtoClass = dtoClass;
  }

  @Override
  public E toEntity(D dto) {
    return Objects.isNull(dto)
        ? null
        : mapper.map(dto, entityClass);
  }

  @Override
  public D toDto(E entity) {
    return Objects.isNull(entity)
        ? null
        : mapper.map(entity, dtoClass);
  }

  @Override
  public List<D> toArraysToDto(List<E> inArray) {
    List<D> outArray = new ArrayList<>();
    inArray.forEach(in -> outArray.add(toDto(in)));
    return outArray;
  }

  protected Converter<E, D> toDtoConverter() {
    return context -> {
      E source = context.getSource();
      D destination = context.getDestination();
      mapSpecificFields(source, destination);
      return context.getDestination();
    };
  }

  protected Converter<D, E> toEntityConverter() {
    return context -> {
      D source = context.getSource();
      E destination = context.getDestination();
      mapSpecificFields(source, destination);
      return context.getDestination();
    };
  }

  protected void mapSpecificFields(E source, D destination) {
  }

  protected void mapSpecificFields(D source, E destination) {
  }

  @Autowired
  public void setMapper(ModelMapper mapper) {
    this.mapper = mapper;
  }
}
