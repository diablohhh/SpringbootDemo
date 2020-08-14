package com.example.user.entity;

import lombok.Data;

/**
 * @Author ZhangQiangLong
 * @Date 2020/8/14 15:23
 * @Text:
 **/
@Data
public class LoginUser {
    private String username;
    private String password;
    private Boolean rememberMe;
}
