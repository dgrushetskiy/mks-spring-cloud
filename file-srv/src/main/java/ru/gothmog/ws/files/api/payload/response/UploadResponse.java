package ru.gothmog.ws.files.api.payload.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.gothmog.ws.files.api.dto.UploadFileDto;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UploadResponse {
    private UploadFileDto file;
}
