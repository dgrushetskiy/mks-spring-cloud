package ru.gothmog.ws.auth.api.dto.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.gothmog.ws.auth.api.dto.AbstractDto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link ru.gothmog.ws.auth.core.model.auth.Role} entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonRootName(value = "role")
@JsonInclude(Include.NON_NULL)
public class RoleDto extends AbstractDto {

  @Size(max = 200)
  @NotNull
  private String roleName;
  @Size(max = 512)
  @NotNull
  private String description;

  private List<UserRoleDto> userRoles;
}
