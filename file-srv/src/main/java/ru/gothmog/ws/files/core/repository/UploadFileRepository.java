package ru.gothmog.ws.files.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.gothmog.ws.files.core.model.UploadFile;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UploadFileRepository extends JpaRepository<UploadFile, Long>, JpaSpecificationExecutor<UploadFile> {

    boolean existsById(Long id);

    boolean existsByPublicId(UUID publicId);

    boolean existsByChecksum(String checksum);

    Optional<UploadFile> findById(Long id);

    Optional<UploadFile> findByPublicId(UUID publicId);
}