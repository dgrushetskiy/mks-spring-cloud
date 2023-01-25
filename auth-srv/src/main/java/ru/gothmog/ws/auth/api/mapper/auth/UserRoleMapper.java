package ru.gothmog.ws.auth.api.mapper.auth;

import org.modelmapper.ModelMapper;
import ru.gothmog.ws.auth.api.dto.auth.UserRoleDto;
import ru.gothmog.ws.auth.api.mapper.AbstractMapper;
import ru.gothmog.ws.auth.api.mapper.MapperConverter;
import ru.gothmog.ws.auth.core.model.auth.UserRole;
import ru.gothmog.ws.auth.core.repository.RoleRepository;
import ru.gothmog.ws.auth.core.repository.UserRepository;

import javax.annotation.PostConstruct;
import java.util.Objects;

@MapperConverter
public class UserRoleMapper extends AbstractMapper<UserRole, UserRoleDto> {

  private final ModelMapper mapper;

  private final UserRepository userRepository;

  private final RoleRepository roleRepository;

  public UserRoleMapper(ModelMapper mapper, UserRepository userRepository,
      RoleRepository roleRepository) {
    super(UserRole.class, UserRoleDto.class);
    this.mapper = mapper;
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
  }

  @PostConstruct
  public void setMapper() {
    mapper.createTypeMap(UserRole.class, UserRoleDto.class)
        .addMappings(m -> m.skip(UserRoleDto::setRoleId))
        .addMappings(m -> m.skip(UserRoleDto::setUserId))
        .setPostConverter(toDtoConverter());
    mapper.createTypeMap(UserRoleDto.class, UserRole.class)
        .addMappings(m -> m.skip(UserRole::setRole))
        .addMappings(m -> m.skip(UserRole::setUser))
        .setPostConverter(toEntityConverter());
  }

  @Override
  protected void mapSpecificFields(UserRole source, UserRoleDto destination) {
    destination.setRoleId(getRoleId(source));
    destination.setUserId(getUserId(source));
  }

  @Override
  protected void mapSpecificFields(UserRoleDto source, UserRole destination) {
    destination.setRole(roleRepository.findById(source.getRoleId()).orElse(null));
    destination.setUser(userRepository.findById(source.getUserId()).orElse(null));
  }

  private Long getRoleId(UserRole source) {
    return Objects.isNull(source) || Objects.isNull(source.getId())
        ? null
        : source.getRole().getId();
  }

  private Long getUserId(UserRole source) {
    return Objects.isNull(source) || Objects.isNull(source.getId())
        ? null
        : source.getUser().getId();
  }
}
