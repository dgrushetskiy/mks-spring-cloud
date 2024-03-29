package ru.gothmog.ws.files;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import ru.gothmog.ws.files.core.property.FileStorageProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
@EnableDiscoveryClient
public class FileSrvApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileSrvApplication.class, args);
    }

}
