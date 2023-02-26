package ru.gothmog.ws.files.core.service;

import ru.gothmog.ws.files.api.dto.UploadFileDto;

import java.util.UUID;


public interface UploadFileService extends BaseCRUDService<UploadFileDto>{

    boolean existsById(long id);

    boolean existsByUUID(UUID uuid);

    UploadFileDto getByPublicId(UUID publicId);
}
