package ru.gothmog.ws.auth.api.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.gothmog.ws.auth.api.dto.auth.UserDto;
import ru.gothmog.ws.auth.api.payload.response.ContentPageLimitResponse;
import ru.gothmog.ws.auth.core.service.auth.UserService;

@Tag(name = "UserController", description = "Сервис с пользователями")
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController extends ApiController {

  private final UserService userService;

  @Operation(summary = "Список - пользователей")
  @GetMapping(path = "/", produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<List<UserDto>> getAllUsers() {
    final List<UserDto> users = userService.getList();
    if (users.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(users, HttpStatus.OK);
  }

  @Operation(summary = "Список - пользователей пагинация")
  @GetMapping(path = "/pages", produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<ContentPageLimitResponse<UserDto>> getAllUserPagesLimit(
      @RequestParam(value = "page", defaultValue = DEFAULT_PAGE_NUMBER) int page,
      @RequestParam(value = "limit", defaultValue = DEFAULT_PAGE_SIZE) int limit) {
    final ContentPageLimitResponse<UserDto> contents = userService.getPageLimit(page, limit);
    if (contents.getContent().isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(contents, HttpStatus.OK);
  }

  @Operation(summary = "Выбрать пользователя по id")
  @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<UserDto> getUserById(@PathVariable("id") long id) {
    final UserDto userById = userService.getById(id);
    return new ResponseEntity<>(userById, HttpStatus.OK);
  }

  @Operation(summary = "Выбрать пользователя по uuid")
  @GetMapping(path = "/{uuid}", produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<UserDto> getUserByUuid(@PathVariable("uuid") UUID uuid) {
    final UserDto userByUuid = userService.getUserByUuid(uuid);
    return new ResponseEntity<>(userByUuid, HttpStatus.OK);
  }

  @Operation(summary = "Выбрать пользователя по email")
  @GetMapping(path = "/{email}", produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<UserDto> getUserByEmail(@PathVariable("email") String email) {
    final UserDto userByEmail = userService.getUserByEmail(email);
    return new ResponseEntity<>(userByEmail, HttpStatus.OK);
  }

  @Operation(summary = "Выбрать пользователя по username")
  @GetMapping(path = "/{username}", produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<UserDto> getUserByUsername(@PathVariable("username") String username) {
    final UserDto userByUsername = userService.getUserByUsername(username);
    return new ResponseEntity<>(userByUsername, HttpStatus.OK);
  }

  @Operation(summary = "Выбрать пользователя по phone")
  @GetMapping(path = "/{phone}", produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<UserDto> getUserByPhone(@PathVariable("phone") String phone) {
    final UserDto userByPhone = userService.getUserByPhone(phone);
    return new ResponseEntity<>(userByPhone, HttpStatus.OK);
  }

  @Operation(summary = "Создать пользователя")
  @PostMapping(path = "/", produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userIn) {
    final UserDto userCreate = userService.create(userIn);
    return new ResponseEntity<>(userCreate, HttpStatus.CREATED);
  }

  @Operation(summary = "Обновить пользователя по id")
  @PatchMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<UserDto> updateUser(@PathVariable("id") long id,
      @RequestBody UserDto userIn) {
    final UserDto userUpdate = userService.update(id, userIn);
    return new ResponseEntity<>(userUpdate, HttpStatus.OK);
  }

  @Operation(summary = "Удалить пользователя по id")
  @DeleteMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<UserDto> deleteUser(@PathVariable("id") long id) {
    final UserDto userDelete = userService.delete(id);
    return new ResponseEntity<>(userDelete, HttpStatus.OK);
  }

}
