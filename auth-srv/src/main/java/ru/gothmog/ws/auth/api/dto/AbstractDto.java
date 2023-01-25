package ru.gothmog.ws.auth.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public abstract class AbstractDto implements Serializable {

  @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
  private Long id;

  @JsonProperty(value = "createdAt", access = JsonProperty.Access.READ_ONLY)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
  private LocalDateTime createdAt;

  @JsonProperty(value = "updatedAt", access = JsonProperty.Access.READ_ONLY)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
  private LocalDateTime updatedAt;

  @JsonProperty(value = "active", access = JsonProperty.Access.READ_ONLY)
  private Boolean active = true;
}
