package ru.gothmog.ws.files.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;
import ru.gothmog.ws.files.core.model.UploadFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.UUID;

/**
 * A DTO for the {@link UploadFile} entity
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonRootName(value = "uploadFile")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UploadFileDto extends AbstractDto {
    @NotNull
    @JsonProperty(value = "publicId", access = JsonProperty.Access.READ_ONLY)
    private UUID publicId = UUID.randomUUID();

    @NotNull
    @JsonProperty(value = "checksum", access = JsonProperty.Access.READ_ONLY)
    private String checksum;
    @JsonProperty(value = "deleted", access = JsonProperty.Access.READ_ONLY)
    private Boolean deleted = false;
    @NotNull
    @JsonProperty(value = "fullName", access = JsonProperty.Access.READ_ONLY)
    private String fullName;
    @NotNull
    @JsonProperty(value = "fileType", access = JsonProperty.Access.READ_ONLY)
    private String fileType;
    @NotNull
    @JsonProperty(value = "uploadLink", access = JsonProperty.Access.READ_ONLY)
    private String uploadLink;
    @NotNull
    @PositiveOrZero
    @JsonProperty(value = "size", access = JsonProperty.Access.READ_ONLY)
    private Long size;
}