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
import java.util.UUID;

/**
 * A DTO for the {@link ru.gothmog.ws.auth.core.model.auth.UserBill} entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonRootName(value = "user_bill")
@JsonInclude(Include.NON_NULL)
public class UserBillDto extends AbstractDto {

  @NotNull
  private UUID billUuid;
  @NotNull
  private Long userId;
}
