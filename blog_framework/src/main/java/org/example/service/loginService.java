package org.example.service;

import org.example.domain.ResponseResult;
import org.example.domain.enity.User;
import org.springframework.stereotype.Service;


public interface loginService {
    ResponseResult login(User user);

    ResponseResult logout();
}
