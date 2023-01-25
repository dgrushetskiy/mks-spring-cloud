package ru.gothmog.ws.auth.api.mapper.auth;

import ru.gothmog.ws.auth.api.dto.auth.RoleDto;
import ru.gothmog.ws.auth.api.mapper.AbstractMapper;
import ru.gothmog.ws.auth.api.mapper.MapperConverter;
import ru.gothmog.ws.auth.core.model.auth.Role;

@MapperConverter
public class RoleMapper extends AbstractMapper<Role, RoleDto> {

  public RoleMapper() {
    super(Role.class, RoleDto.class);
  }
}
