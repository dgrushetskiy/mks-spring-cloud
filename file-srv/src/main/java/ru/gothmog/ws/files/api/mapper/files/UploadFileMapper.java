package ru.gothmog.ws.files.api.mapper.files;

import ru.gothmog.ws.files.api.dto.UploadFileDto;
import ru.gothmog.ws.files.api.mapper.AbstractMapper;
import ru.gothmog.ws.files.api.mapper.MapperConverter;
import ru.gothmog.ws.files.core.model.UploadFile;

@MapperConverter
public class UploadFileMapper extends AbstractMapper<UploadFile, UploadFileDto> {
    public UploadFileMapper() {
        super(UploadFile.class, UploadFileDto.class);
    }
}
