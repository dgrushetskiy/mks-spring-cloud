package ru.gothmog.ws.auth.api.mapper.auth;

import ru.gothmog.ws.auth.api.dto.auth.UserDto;
import ru.gothmog.ws.auth.api.mapper.AbstractMapper;
import ru.gothmog.ws.auth.api.mapper.MapperConverter;
import ru.gothmog.ws.auth.core.model.auth.User;

@MapperConverter
public class UserMapper extends AbstractMapper<User, UserDto> {

  public UserMapper() {
    super(User.class, UserDto.class);
  }
}
