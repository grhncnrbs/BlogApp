package org.grhncnrbs.blog.exception;

import org.grhncnrbs.blog.dto.DbsResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalException {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DbsResponseEntity> handlingValidationException(MethodArgumentNotValidException methodArgumentNotValidException) {
       DbsResponseEntity dbsResponseEntity = new DbsResponseEntity();
       String errorMessage = methodArgumentNotValidException.getBindingResult().getFieldError().getDefaultMessage();
       dbsResponseEntity.setMessage(errorMessage);
       return new ResponseEntity<>(dbsResponseEntity, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<DbsResponseEntity> handlingRecordNotFoundException(RecordNotFoundException recordNotFoundException) {
        DbsResponseEntity dbsResponseEntity = new DbsResponseEntity();
        dbsResponseEntity.setMessage(recordNotFoundException.getMessage());
        return new ResponseEntity<>(dbsResponseEntity, HttpStatus.BAD_REQUEST);
    }
}
