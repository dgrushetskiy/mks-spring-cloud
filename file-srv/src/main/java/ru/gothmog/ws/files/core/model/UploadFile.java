package ru.gothmog.ws.files.core.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

import javax.persistence.Index;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import java.util.UUID;

@Entity
@Table(name = "upload_files", schema = "files", indexes = {
        @Index(name = "idx_file_uuid_unq", columnList = "public_id", unique = true),
        @Index(name = "idx_file_checksum_unq", columnList = "checksum", unique = true)
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UploadFile extends AbstractEntity{

    @Column(name = "public_id", nullable = false, unique = true)
    private UUID publicId;

    @Column(name = "checksum", nullable = false, unique = true)
    private String checksum;

    @Column(name = "deleted", nullable = false)
    private Boolean deleted = false;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "file_type", nullable = false)
    private String fileType;

    @Column(name = "upload_link", nullable = false, columnDefinition = "TEXT")
    private String uploadLink;

    @Column(name = "size", nullable = false)
    private Long size;
}