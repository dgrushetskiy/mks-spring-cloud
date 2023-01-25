package ru.gothmog.ws.auth.api.mapper.auth;

import java.util.Objects;
import javax.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import ru.gothmog.ws.auth.api.dto.auth.UserBillDto;
import ru.gothmog.ws.auth.api.mapper.AbstractMapper;
import ru.gothmog.ws.auth.api.mapper.MapperConverter;
import ru.gothmog.ws.auth.core.model.auth.UserBill;
import ru.gothmog.ws.auth.core.repository.UserRepository;

@MapperConverter
public class UserBillMapper extends AbstractMapper<UserBill, UserBillDto> {

  private final ModelMapper mapper;
  private final UserRepository userRepository;

  public UserBillMapper(ModelMapper mapper, UserRepository userRepository) {
    super(UserBill.class, UserBillDto.class);
    this.mapper = mapper;
    this.userRepository = userRepository;
  }

  @PostConstruct
  public void setupMapper() {
    mapper.createTypeMap(UserBill.class, UserBillDto.class)
        .addMappings(m -> m.skip(UserBillDto::setUserId))
        .setPostConverter(toDtoConverter());
    mapper.createTypeMap(UserBillDto.class, UserBill.class)
        .addMappings(m -> m.skip(UserBill::setUser))
        .setPostConverter(toEntityConverter());
  }

  @Override
  protected void mapSpecificFields(UserBill source, UserBillDto destination) {
    destination.setUserId(getUserId(source));
  }

  @Override
  protected void mapSpecificFields(UserBillDto source, UserBill destination) {
    destination.setUser(userRepository.findById(source.getUserId()).orElse(null));
  }

  private Long getUserId(UserBill source) {
    return Objects.isNull(source) || Objects.isNull(source.getId())
        ? null
        : source.getUser().getId();
  }
}
