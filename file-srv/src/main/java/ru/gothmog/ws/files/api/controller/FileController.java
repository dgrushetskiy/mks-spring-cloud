package ru.gothmog.ws.files.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.tika.Tika;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.gothmog.ws.files.api.dto.UploadFileDto;
import ru.gothmog.ws.files.core.service.UploadFileService;
import ru.gothmog.ws.files.core.service.impl.FileStorageService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Tag(name = "FileController", description = "Файлы хранилище")
@Slf4j
@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileController {

    private final FileStorageService fileStorageService;
    private final UploadFileService uploadFileService;

    @SneakyThrows
    @Operation(summary = "Загрузка файла в директорию сервиса хранилища ", description = "Загрузка файла в s3")
    @PostMapping(path = "/uploadFile")
    public ResponseEntity<UploadFileDto> uploadFile(@RequestParam("file") MultipartFile file) {
        UploadFileDto uploadFile = getUploadFile(file);
        return new ResponseEntity<>(uploadFile, HttpStatus.OK);
    }

    @SneakyThrows
    @Operation(summary = "Загрузка нескольких файлов в директорию сервиса хранилища ", description = "Загрузка файлов в s3")
    @PostMapping(path = "/uploadMultipleFiles")
    public ResponseEntity<List<UploadFileDto>> uploadMultiFiles(@RequestParam("files") MultipartFile[] files) {
        final List<UploadFileDto> uploadFiles = Arrays.asList(files)
                .stream()
                .map(file -> {
                    try {
                        return getUploadFile(file);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
        return new ResponseEntity<>(uploadFiles, HttpStatus.OK);
    }

    @GetMapping(value = "/downloadFile//{fileId}")
    public ResponseEntity<Resource> getUploadFileByPublicID(@PathVariable UUID fileId, HttpServletRequest request) {
        UploadFileDto uploadFileByPublicID = uploadFileService.getByPublicId(fileId);
        String fileName = uploadFileByPublicID.getFullName();
        Resource resource = fileStorageService.loadFileAsResource(fileName);
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            log.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    private UploadFileDto getUploadFile(@NonNull final MultipartFile file) throws IOException {
        final String fileName = fileStorageService.storeFile(file);
        InputStream is = file.getInputStream();
        String checksum = DigestUtils.sha3_512Hex(is);
        Tika tika = new Tika();
        String detectedType = tika.detect(file.getBytes());

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();
        final UploadFileDto uploadFile = UploadFileDto
                .builder()
                .checksum(checksum)
                .fullName(fileName)
                .fileType(detectedType)
                .uploadLink(fileDownloadUri)
                .size(file.getSize())
                .build();
        uploadFileService.create(uploadFile);
        return uploadFile;
    }


}
