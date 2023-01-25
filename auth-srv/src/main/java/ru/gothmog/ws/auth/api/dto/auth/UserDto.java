package ru.gothmog.ws.auth.api.dto.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.gothmog.ws.auth.api.dto.AbstractDto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

/**
 * A DTO for the {@link ru.gothmog.ws.auth.core.model.auth.User} entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonRootName(value = "user")
@JsonInclude(Include.NON_NULL)
public class UserDto extends AbstractDto {

  @NotNull
  @JsonProperty(value = "uuid", access = JsonProperty.Access.READ_ONLY)
  private UUID uuid = UUID.randomUUID();

  @Size(max = 200)
  @NotNull(message = "Поле username должно быть задано")
  @JsonProperty(value = "username")
  private String username;
  @Size(max = 200)
  @NotNull(message = "Поле email должно быть задано")
  @Email(regexp = "^(?:[a-zA-Z0-9_'^&/+-])+(?:\\.(?:[a-zA-Z0-9_'^&/+-])+)" +
      "*@(?:(?:\\[?(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?))\\.)" +
      "{3}(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\]?)|(?:[a-zA-Z0-9-]+\\.)" +
      "+(?:[a-zA-Z]){2,}\\.?)$", message = "заданный email не может существовать")
  @JsonProperty(value = "email")
  private String email;
  @Size(max = 512)
  @NotNull
  @JsonProperty(value = "password", access = JsonProperty.Access.WRITE_ONLY)
  private String passwd;
  @Size(max = 15)
  @NotNull(message = "Поле phone должно быть задано")
  @JsonProperty(value = "phone")
  private String phone;
  @JsonProperty(value = "user_roles", access = JsonProperty.Access.READ_ONLY)
  private List<UserRoleDto> userRoles;
  @JsonProperty(value = "user_bills", access = JsonProperty.Access.READ_ONLY)
  private List<UserBillDto> userBills;
}
