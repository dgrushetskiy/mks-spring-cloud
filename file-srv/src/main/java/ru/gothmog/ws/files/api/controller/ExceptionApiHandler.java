package ru.gothmog.ws.files.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import ru.gothmog.ws.files.core.exception.BadRequestException;
import ru.gothmog.ws.files.core.exception.ConflictException;
import ru.gothmog.ws.files.core.exception.ErrorMessage;
import ru.gothmog.ws.files.core.exception.FileNotFoundException;

@Slf4j
@RestControllerAdvice
public class ExceptionApiHandler {

  @ExceptionHandler(FileNotFoundException.class)
  public ResponseEntity<ErrorMessage> notFoundException(FileNotFoundException exception) {
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
  public ResponseEntity<ErrorMessage> conflictException(ConflictException exception) {
    log.error(exception.getMessage(), exception);
    return ResponseEntity.status(HttpStatus.CONFLICT)
        .body(new ErrorMessage(exception.getMessage()));
  }

  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<ErrorMessage> badRequestException(BadRequestException exception) {
    log.error(exception.getMessage(), exception);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(new ErrorMessage(exception.getMessage()));
  }
}
