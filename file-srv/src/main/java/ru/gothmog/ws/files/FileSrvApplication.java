package ru.gothmog.ws.files;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.gothmog.ws.files.core.property.FileStorageProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class FileSrvApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileSrvApplication.class, args);
    }

}
