package com.example.user.controller;

import com.example.framework.exception.ValidException;
import com.example.user.entity.S_user;
import com.example.user.server.S_userServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author ZhangQiangLong
 * @Date 2020/7/2 11:57
 * @Text:
 **/
@RestController
@RequestMapping("/api/s_user")
public class S_userController {

    private static final Logger logger = LoggerFactory.getLogger(S_userController.class);

    @Autowired
    private S_userServer s_userServer;

    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)
    public ResponseEntity<List<S_user>> getUsers() {
        logger.info("i get");
        return ResponseEntity.ok(s_userServer.getUsers());
    }

    @RequestMapping(value = "/getUserByUserid/{userid}", method = RequestMethod.GET)
    public ResponseEntity<S_user> getUserByUserid(@PathVariable("userid") String userid) {
        if (logger.isDebugEnabled()) {
            logger.debug("userid is :" + userid);
        }
        S_user s_user = s_userServer.getUserByUserid(userid);
        return ResponseEntity.ok(s_user);
    }

    @RequestMapping(value = "/getUserByUsername/{username}", method = RequestMethod.GET)
    public ResponseEntity<S_user> getUserByUsername(@PathVariable("username") String username) {
        if (logger.isDebugEnabled()) {
            logger.debug("username is :" + username);
        }
        S_user s_user = s_userServer.getUserByUsername(username);
        return ResponseEntity.ok(s_user);
    }

    @RequestMapping(value = "/saveData", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity create(@RequestBody @Valid S_user entity, BindingResult result) throws ValidException {
        if (result.hasErrors()) {
            throw new ValidException(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(s_userServer.saveByEntity(entity));
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = "application/json")
    public ResponseEntity<S_user> update(@RequestBody @Valid S_user entity, BindingResult result) throws ValidException {
        if (result.hasErrors()) {
            throw new ValidException(result);
        }
        return ResponseEntity.ok(s_userServer.updateByEntity(entity));
    }

    @RequestMapping(value = "/delete/{userid}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable("userid") String userid) {
        if (logger.isDebugEnabled()) {
            logger.debug("userid is :" + userid);
        }
        s_userServer.deleteByUserid(userid);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
