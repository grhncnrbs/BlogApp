package org.grhncnrbs.blog.service;

import lombok.extern.slf4j.Slf4j;
import org.grhncnrbs.blog.dto.LoginUserRequest;
import org.grhncnrbs.blog.dto.RegisterUserRequest;
import org.grhncnrbs.blog.exception.AuthenticationFailedException;
import org.grhncnrbs.blog.exception.RecordNotFoundException;
import org.grhncnrbs.blog.exception.UserAlreadyRegisterException;
import org.grhncnrbs.blog.model.User;
import org.grhncnrbs.blog.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(RegisterUserRequest registerUserRequest) {
        User userTemp = userRepository.findByUserName(registerUserRequest.getUserName());
        if (!Objects.isNull(userTemp)) {
            throw new UserAlreadyRegisterException("Email already present in system. Please try with forgot password.");
        }
        User user = new User();
        BeanUtils.copyProperties(registerUserRequest, user);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    public User login(LoginUserRequest loginUserRequest) {
        User userTemp = userRepository.findByUserName(loginUserRequest.getUserName());
        if (!Objects.isNull(userTemp)) {
            throw new RecordNotFoundException("Email is not yet registered.Please register and try again.");
        }
        User user = userRepository.findByUserNameAndPassword(loginUserRequest.getUserName(), loginUserRequest.getPassword());
        if (Objects.isNull(user)) {
            throw new AuthenticationFailedException("Password or username  is incorrect.");
        }
        return user;
    }
}
