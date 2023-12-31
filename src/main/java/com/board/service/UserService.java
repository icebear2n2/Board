package com.board.service;

import com.board.dao.UserDao;
import com.board.domain.request.LoginRequest;
import com.board.domain.request.SignupRequest;
import com.board.domain.response.LoginResponse;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public int signup(SignupRequest signupRequest) throws Exception {
        if (!Pattern.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$", signupRequest.getPassword())) {
            throw new Exception("비밀번호는 최소 8자리에 숫자, 문자, 특수문자 각 1개 이상 포함 되어야 합니다");
        } else {
            return userDao.signup(signupRequest);
        }
    }


    public LoginResponse login(LoginRequest loginRequest) {
        try {
            return userDao.login(loginRequest);
        } catch (Exception e) {
            return null;
        }
    }
}