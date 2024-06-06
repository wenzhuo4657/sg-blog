package org.example.service;

import org.example.domain.ResponseResult;
import org.example.domain.enity.User;


public interface ADIM_loginService {
    ResponseResult login(User user);

    ResponseResult logout();
}
