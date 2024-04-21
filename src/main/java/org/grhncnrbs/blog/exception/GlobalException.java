package org.grhncnrbs.blog.exception;

import lombok.extern.slf4j.Slf4j;
import org.grhncnrbs.blog.dto.DbsResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
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

    @ExceptionHandler(UserAlreadyRegisterException.class)
    public ResponseEntity<DbsResponseEntity> handlingUserAlreadyRegisterException(UserAlreadyRegisterException  userAlreadyRegisterException) {
        DbsResponseEntity dbsResponseEntity=new DbsResponseEntity();
        dbsResponseEntity.setMessage(userAlreadyRegisterException.message);
        log.debug("GlobalException:handleUserAlreadyRegisterException user already present in database.");
        return new ResponseEntity<>(dbsResponseEntity,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AuthenticationFailedException.class)
    public ResponseEntity<DbsResponseEntity> handleAuthenticationFailedException(AuthenticationFailedException  authenticationFailedException) {
        DbsResponseEntity dbsResponseEntity=new DbsResponseEntity();
        dbsResponseEntity.setMessage(authenticationFailedException.message);
        log.debug("GlobalException:handleUserAlreadyRegisterException Authentication failed.");
        return new ResponseEntity<>(dbsResponseEntity,HttpStatus.UNAUTHORIZED);
    }
}
