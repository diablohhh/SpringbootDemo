package com.example.user.server;

import com.example.user.entity.S_role;
import com.example.user.repository.S_roleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @Author ZhangQiangLong
 * @Date 2020/8/18 15:56
 * @Text:
 **/
@Service
@Transactional
public class S_roleServer{

    @Autowired
    private final S_roleRepository dao;


    public S_roleServer(S_roleRepository dao) {
        this.dao = dao;
    }

    public S_role getRoleByRoleName(String rolename){
        return dao.findFirstByRolename(rolename);
    }
}
