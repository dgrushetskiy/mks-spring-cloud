package ru.gothmog.ws.auth.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import ru.gothmog.ws.auth.core.exception.BadRequestException;
import ru.gothmog.ws.auth.core.exception.ConflictException;
import ru.gothmog.ws.auth.core.exception.ErrorMessage;
import ru.gothmog.ws.auth.core.exception.NotFoundException;

@Slf4j
@RestControllerAdvice
public class ExceptionApiHandler {

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ErrorMessage> notFoundException(NotFoundException exception) {
    log.error(exception.getMessage(), exception);
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(new ErrorMessage(exception.getMessage()));
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<ErrorMessage> mismatchException(
      MethodArgumentTypeMismatchException exception) {
    log.error(exception.getMessage(), exception);
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(new ErrorMessage(exception.getMessage()));
  }

  @ExceptionHandler(ConflictException.class)
  public ResponseEntity<ErrorMessage> conflictException(NotFoundException exception) {
    log.error(exception.getMessage(), exception);
    return ResponseEntity.status(HttpStatus.CONFLICT)
        .body(new ErrorMessage(exception.getMessage()));
  }

  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<ErrorMessage> badRequestException(NotFoundException exception) {
    log.error(exception.getMessage(), exception);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(new ErrorMessage(exception.getMessage()));
  }
}
