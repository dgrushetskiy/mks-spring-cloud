package ru.gothmog.ws.files.core.service.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.gothmog.ws.files.api.dto.UploadFileDto;
import ru.gothmog.ws.files.api.mapper.files.UploadFileMapper;
import ru.gothmog.ws.files.core.exception.ConflictException;
import ru.gothmog.ws.files.core.exception.FileNotFoundException;
import ru.gothmog.ws.files.core.model.UploadFile;
import ru.gothmog.ws.files.core.repository.UploadFileRepository;
import ru.gothmog.ws.files.core.service.UploadFileService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UploadFileServiceImpl implements UploadFileService {

    private static final String RECORD_ALREADY_EXISTS_MESSAGE = "Record checksum file already exists";
    private static final String RECORD_NOT_FOUND_BY_ID_MESSAGE = "Record file by id is not found";
    private static final String RECORD_NOT_FOUND_BY_PUBLIC_ID_MESSAGE = "Record file by public id is not found";

    private final UploadFileRepository repository;
    private final UploadFileMapper fileMapper;

    @Override
    public boolean existsById(long id) {
        return repository.existsById(id);
    }

    @Override
    public boolean existsByUUID(UUID uuid) {
        return repository.existsByPublicId(uuid);
    }

    @Override
    public UploadFileDto create(@NonNull final UploadFileDto dto) {
        final String fileChecksum = dto.getChecksum();
        if (repository.existsByChecksum(fileChecksum)) {
            throw new ConflictException(RECORD_ALREADY_EXISTS_MESSAGE, "checksum", fileChecksum);
        }
        final UploadFile createFile = fileMapper.toEntity(dto);
        repository.save(createFile);
        log.info("file created: {} ", createFile);
        return dto;
    }

    @Override
    public UploadFileDto getById(long id) {
        final Optional<UploadFile> optionalUploadFileById = repository.findById(id);
        if (optionalUploadFileById.isEmpty()) {
            throw new FileNotFoundException(RECORD_NOT_FOUND_BY_ID_MESSAGE, "id", id);
        }
        final UploadFile uploadFileById = optionalUploadFileById.get();
        log.info("File found by id : {} ", uploadFileById);
        return fileMapper.toDto(uploadFileById);
    }

    @Override
    public UploadFileDto getByPublicId(@NonNull final UUID publicId) {
        final Optional<UploadFile> optionalUploadFileByPublicId = repository.findByPublicId(publicId);
        if (optionalUploadFileByPublicId.isEmpty()) {
            throw new FileNotFoundException(RECORD_NOT_FOUND_BY_PUBLIC_ID_MESSAGE, "publicId", publicId);
        }
        final UploadFile uploadFileByPublicId = optionalUploadFileByPublicId.get();
        log.info("File found by publicId : {} ", uploadFileByPublicId);
        return fileMapper.toDto(uploadFileByPublicId);
    }

    @Override
    public UploadFileDto delete(long id) {
        final Optional<UploadFile> optionalUploadFileById = repository.findById(id);
        if (optionalUploadFileById.isEmpty()) {
            throw new FileNotFoundException(RECORD_NOT_FOUND_BY_ID_MESSAGE, "id", id);
        }
        final UploadFile uploadFileByIdDeleted = optionalUploadFileById.get();
        repository.delete(uploadFileByIdDeleted);
        log.info("File deleted: {} ", uploadFileByIdDeleted);
        return fileMapper.toDto(uploadFileByIdDeleted);
    }

    @Override
    public UploadFileDto update(long id, UploadFileDto dto) {
        final Optional<UploadFile> optionalUploadFileById = repository.findById(id);
        if (optionalUploadFileById.isEmpty()) {
            throw new FileNotFoundException(RECORD_NOT_FOUND_BY_ID_MESSAGE, "id", id);
        }
        final UploadFile uploadFileByIdUpdated = optionalUploadFileById.get();
        uploadFileByIdUpdated.setChecksum(dto.getChecksum());
        uploadFileByIdUpdated.setDeleted(dto.getDeleted());
        uploadFileByIdUpdated.setFullName(dto.getFullName());
        uploadFileByIdUpdated.setFileType(dto.getFileType());
        uploadFileByIdUpdated.setUploadLink(dto.getUploadLink());
        uploadFileByIdUpdated.setSize(dto.getSize());
        repository.save(uploadFileByIdUpdated);
        log.info("File updated: {} ", uploadFileByIdUpdated);
        return fileMapper.toDto(uploadFileByIdUpdated);
    }

    @Override
    public Page<UploadFileDto> getPageToLimit(int page, int limit) {
        Pageable pageable = getPageable(page, limit);
        Page<UploadFile> pageFiles = repository.findAll(pageable);
        return pageFiles.map(fileMapper::toDto);
    }

    @Override
    public List<UploadFileDto> getList() {
        final List<UploadFile> files = repository.findAll();
        return fileMapper.toArraysToDto(files);
    }

    private Pageable getPageable(int page, int limit) {
        if (page > 0) {
            page = page - 1;
        }
        Pageable pageable = PageRequest.of(page, limit);
        return pageable;
    }
}
