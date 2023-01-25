package ru.gothmog.ws.auth.core.service.auth.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.gothmog.ws.auth.api.dto.auth.UserDto;
import ru.gothmog.ws.auth.api.mapper.auth.UserMapper;
import ru.gothmog.ws.auth.api.payload.response.ContentPageLimitResponse;
import ru.gothmog.ws.auth.core.exception.ConflictException;
import ru.gothmog.ws.auth.core.exception.NotFoundException;
import ru.gothmog.ws.auth.core.model.auth.User;
import ru.gothmog.ws.auth.core.repository.UserRepository;
import ru.gothmog.ws.auth.core.service.auth.UserService;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private static final String ID = "id";
  private static final String UUID = "uuid";
  private static final String EMAIL = "email";
  private static final String PHONE = "phone";
  private static final String USERNAME = "username";
  private static final String ALREADY_EXISTS = " already exists";
  private static final String RECORD_USER_BY = "Record User by ";
  private static final String NOT_FOUND = " not found";
  private static final String RECORD_USER_BY_EMAIL = RECORD_USER_BY + EMAIL + ALREADY_EXISTS;
  private static final String RECORD_USER_BY_USERNAME = RECORD_USER_BY + USERNAME + ALREADY_EXISTS;
  private static final String RECORD_USER_BY_ID_NOT_FOUND = RECORD_USER_BY + ID + NOT_FOUND;
  private static final String RECORD_USER_BY_UUID_NOT_FOUND = RECORD_USER_BY + UUID + NOT_FOUND;
  private static final String RECORD_USER_BY_EMAIL_NOT_FOUND = RECORD_USER_BY + EMAIL + NOT_FOUND;
  private static final String RECORD_USER_BY_PHONE_NOT_FOUND = RECORD_USER_BY + PHONE + NOT_FOUND;
  private static final String RECORD_USER_BY_USERNAME_NOT_FOUND =
      RECORD_USER_BY + USERNAME + NOT_FOUND;

  private final UserMapper mapper;
  private final UserRepository repository;

  @Override
  public UserDto create(final @NonNull UserDto dto) {
    final String userEmail = dto.getEmail();
    if (repository.existsByEmail(userEmail)) {
      throw new ConflictException(RECORD_USER_BY_EMAIL, EMAIL, userEmail);
    }
    final String username = dto.getUsername();
    if (repository.existsByUsername(username)) {
      throw new ConflictException(RECORD_USER_BY_USERNAME, USERNAME, username);
    }
    final User userSave = repository.save(mapper.toEntity(dto));
    log.info("User created: {}", userSave);
    return dto;
  }

  @Override
  public UserDto createByBillIds(final @NonNull UserDto user, List<Long> billIds) {
    return null;
  }

  @Override
  public UserDto createByBillId(final @NonNull UserDto user, Long billId) {
    return null;
  }

  @Override
  public UserDto getById(long id) {
    final Optional<User> optionalUser = repository.findById(id);
    if (optionalUser.isEmpty()) {
      throw new NotFoundException(RECORD_USER_BY_ID_NOT_FOUND, ID, id);
    }
    final User userById = optionalUser.get();
    log.info("User by id: {}", userById);
    return mapper.toDto(userById);
  }

  @Override
  public UserDto getUserByUuid(final @NonNull UUID uuid) {
    final Optional<User> optionalUser = repository.findByUuid(uuid);
    if (optionalUser.isEmpty()) {
      throw new NotFoundException(RECORD_USER_BY_UUID_NOT_FOUND, UUID, uuid);
    }
    final User userByUuid = optionalUser.get();
    log.info("User by uuid: {}", userByUuid);
    return mapper.toDto(userByUuid);
  }

  @Override
  public UserDto getUserByEmail(final @NonNull String email) {
    final Optional<User> optionalUser = repository.findByEmail(email);
    if (optionalUser.isEmpty()) {
      throw new NotFoundException(RECORD_USER_BY_EMAIL_NOT_FOUND, EMAIL, email);
    }
    final User userByEmail = optionalUser.get();
    log.info("User by email: {}", userByEmail);
    return mapper.toDto(userByEmail);
  }

  @Override
  public UserDto getUserByUsername(final @NonNull String username) {
    final Optional<User> optionalUser = repository.findByUsername(username);
    if (optionalUser.isEmpty()) {
      throw new NotFoundException(RECORD_USER_BY_USERNAME_NOT_FOUND, USERNAME, username);
    }
    final User userByUsername = optionalUser.get();
    log.info("User by username: {}", userByUsername);
    return mapper.toDto(userByUsername);
  }

  @Override
  public UserDto getUserByPhone(final @NonNull String phone) {
    final Optional<User> optionalUser = repository.findByPhone(phone);
    if (optionalUser.isEmpty()) {
      throw new NotFoundException(RECORD_USER_BY_PHONE_NOT_FOUND, PHONE, phone);
    }
    final User userByPhone = optionalUser.get();
    log.info("User by phone: {}", userByPhone);
    return mapper.toDto(userByPhone);
  }

  @Override
  public UserDto delete(long id) {
    final Optional<User> optionalUser = repository.findById(id);
    if (optionalUser.isEmpty()) {
      throw new NotFoundException(RECORD_USER_BY_ID_NOT_FOUND, ID, id);
    }
    final User userIsDeleted = optionalUser.get();
    log.info("User deleted: {}", userIsDeleted);
    return mapper.toDto(userIsDeleted);
  }

  @Override
  public UserDto update(long id, final @NonNull UserDto dto) {
    final Optional<User> optionalUser = repository.findById(id);
    if (optionalUser.isEmpty()) {
      throw new NotFoundException(RECORD_USER_BY_ID_NOT_FOUND, ID, id);
    }
    final User userUpdate = optionalUser.get();
    dto.setUuid(userUpdate.getUuid());
    final User userIsUpdated = repository.save(mapper.toEntity(dto));
    log.info("User updated: {}", userIsUpdated);
    return dto;
  }

  @Override
  public boolean existUserById(Long userId) {
    return repository.existsById(userId);
  }

  @Override
  public boolean existUserByUuid(UUID uuid) {
    return repository.existsByUuid(uuid);
  }



  @Override
  public List<UserDto> getList() {
    final List<User> users = repository.findAll();
    log.info("Users size list: {}", users.size());
    return mapper.toArraysToDto(users);
  }

  @Override
  public List<UserDto> getListPageToLimit(int page, int limit) {
    if (page > 0) {
      page = page - 1;
    }
    Pageable pageable = PageRequest.of(page, limit);
    Page<User> pageUsers = repository.findAll(pageable);
    List<User> users = pageUsers.getContent();
    log.info("Users size list: {}", users.size());
    return mapper.toArraysToDto(users);
  }

  @Override
  public ContentPageLimitResponse<UserDto> getPageLimit(int page, int limit) {
    if (page > 0) {
      page = page - 1;
    }
    Pageable pageable = PageRequest.of(page, limit);
    Page<User> pageUsers = repository.findAll(pageable);
    List<User> users = pageUsers.getContent();
    return ContentPageLimitResponse.<UserDto>builder()
        .content(mapper.toArraysToDto(users))
        .currentPage(pageUsers.getNumber())
        .totalElements(pageUsers.getTotalElements())
        .totalPages(pageUsers.getTotalPages())
        .build();
  }
}
