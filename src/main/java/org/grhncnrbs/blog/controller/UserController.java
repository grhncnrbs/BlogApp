package org.grhncnrbs.blog.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.grhncnrbs.blog.dto.DbsResponseEntity;
import org.grhncnrbs.blog.dto.JwtResponse;
import org.grhncnrbs.blog.dto.LoginUserRequest;
import org.grhncnrbs.blog.dto.RegisterUserRequest;
import org.grhncnrbs.blog.exception.AuthenticationFailedException;
import org.grhncnrbs.blog.exception.RecordNotFoundException;
import org.grhncnrbs.blog.exception.UserAlreadyRegisterException;
import org.grhncnrbs.blog.model.User;
import org.grhncnrbs.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping("v1/register")
    public ResponseEntity<DbsResponseEntity<JwtResponse>> registerCall(@Valid @RequestBody RegisterUserRequest registerUserRequest) {
        DbsResponseEntity dbsResponseEntity = new DbsResponseEntity<>();
        log.info("BlogController: loginCall request received with body: {}", registerUserRequest.toString());
        try {
            User user = userService.registerUser(registerUserRequest);
            JwtResponse jwtResponse = new JwtResponse("Test token.");
            dbsResponseEntity.setData(jwtResponse);
            dbsResponseEntity.setMessage("Registration done successfully please login.");
            return ResponseEntity.ok(dbsResponseEntity);
        } catch (UserAlreadyRegisterException exception) {
            log.debug("BlogController:registerCall user already presenet in system:{}", exception);
            throw exception;
        } catch (Exception exception) {
            log.debug("BlogController:registerCall something went wrong:{}", exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("v1/login")
    public ResponseEntity<DbsResponseEntity<JwtResponse>> loginCall(@Valid @RequestBody LoginUserRequest loginUserRequest) {
        DbsResponseEntity dbsResponseEntity = new DbsResponseEntity<>();
        log.info("BlogController: loginCall request received with body: {}", loginUserRequest.toString());
        try {
            User user = userService.login(loginUserRequest);
            JwtResponse jwtResponse = new JwtResponse("Test token.");
            dbsResponseEntity.setData(jwtResponse);
            dbsResponseEntity.setMessage("User login successfully please login.");
            return ResponseEntity.ok(dbsResponseEntity);
        } catch (RecordNotFoundException exception) {
            log.debug("BlogController:loginCall user noy yet register:{}", exception);
            throw exception;
        } catch (AuthenticationFailedException exception) {
            log.debug("BlogController:loginCall Authentication failed exception:{}", exception);
            throw exception;
        } catch (Exception exception) {
            log.debug("BlogController:loginCall something went wrong:{}", exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}


