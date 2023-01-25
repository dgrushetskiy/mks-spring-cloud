package ru.gothmog.ws.auth.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.health.Health;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "HealthCheckController", description = "HealthCheck")
@RestController
@RequestMapping("/api/v1/actuator")
@RequiredArgsConstructor
public class HealthCheckController {

  @Operation(summary = "Проверка сервиса пользователей", description = "Состояние сервиса пользователей")
  @GetMapping("/health")
  public ResponseEntity<Health> check() {
    Health health = Health.up()
        .withDetail("liveness", "liveness")
        .build();
    return new ResponseEntity<>(health, HttpStatus.OK);
  }
}
