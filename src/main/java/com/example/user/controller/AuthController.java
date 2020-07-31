package com.example.user.controller;

import com.example.framework.exception.ValidException;
import com.example.user.entity.S_user;
import com.example.user.server.S_userServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author ZhangQiangLong
 * @Date 2020/7/30 16:57
 * @Text:
 **/

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private S_userServer s_userServer;

    /**
     * description: 注册默认权限（ROLE_USER）用户
     *
     * @return java.lang.String
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity create(@RequestBody @Valid S_user entity, BindingResult result) throws ValidException {
        if (result.hasErrors()) {
            throw new ValidException(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(s_userServer.saveByEntity(entity));
    }
}
