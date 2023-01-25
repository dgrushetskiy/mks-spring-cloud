package ru.gothmog.ws.auth.core.service.auth;

import java.util.List;
import java.util.UUID;
import ru.gothmog.ws.auth.api.dto.auth.UserDto;
import ru.gothmog.ws.auth.api.payload.response.ContentPageLimitResponse;
import ru.gothmog.ws.auth.core.service.BaseCRUDService;

public interface UserService extends BaseCRUDService<UserDto> {

  boolean existUserById(Long userId);

  boolean existUserByUuid(UUID uuid);

  UserDto createByBillIds(UserDto user, List<Long> billIds);

  UserDto createByBillId(UserDto user, Long billId);

  UserDto getUserByUuid(UUID uuid);

  UserDto getUserByEmail(String email);

  UserDto getUserByUsername(String username);

  UserDto getUserByPhone(String phone);

  ContentPageLimitResponse<UserDto> getPageLimit(int page, int limit);
}
