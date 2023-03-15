package ru.gothmog.ws.documents;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DocumentsSrvApplication {

    public static void main(String[] args) {
        SpringApplication.run(DocumentsSrvApplication.class, args);
    }

}
