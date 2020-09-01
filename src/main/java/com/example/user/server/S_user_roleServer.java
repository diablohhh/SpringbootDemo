package com.example.user.server;

import com.example.user.entity.S_user_role;
import com.example.user.repository.S_user_roleRepository;
import org.springframework.stereotype.Service;

/**
 * @Author ZhangQiangLong
 * @Date 2020/8/18 16:44
 * @Text:
 **/
@Service
public class S_user_roleServer {

    private final S_user_roleRepository dao;

    public S_user_roleServer(S_user_roleRepository dao) {
        this.dao = dao;
    }

    public S_user_role saveByEntity(S_user_role s_user_role){
        return  dao.save(s_user_role);
    }
}
