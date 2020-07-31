package com.example.user.server;

import com.example.user.entity.S_user;
import com.example.user.repository.S_roleRepository;
import com.example.user.repository.S_userRepository;
import com.example.user.repository.S_user_roleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author ZhangQiangLong
 * @Date 2020/7/2 11:53
 * @Text:
 **/
@Service
@Transactional
public class S_userServer {

    @Autowired
    private final S_userRepository dao;
    @Autowired
    private S_user_roleRepository s_user_roleRepository;

    /*@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;*/

    public S_userServer(S_userRepository dao) {
        this.dao = dao;
    }

    public List<S_user> getUsers() {
        return dao.findAll();
    }

    public List<String> getRolesByUserName(String userName) {
        return dao.getRolesByUsername(userName);
    }

    public S_user saveByEntity(S_user s_user) {
        /*s_user.setPassword(bCryptPasswordEncoder.encode(s_user.getPassword()));*/
        List<String> roles = new ArrayList<>();
        roles.add("ROLE_USER");
        s_user.setRoles(roles);
        S_user result = dao.save(s_user);
        if(!StringUtils.isEmpty(result.getUsername())){
        // 插入用户成功时生成用户的角色信息
            s_user_roleRepository.saveEntity(result.getUsername(),"ROLE_USER");
            result.setRoles(roles);
        }
        return result;
    }

    public S_user getUserByUserid(String userid) {
        return dao.findByUserid(userid);
    }

    public S_user getUserByUsername(String username) {
        return dao.findByUsername(username);
    }

    public void deleteByUserid(String userid) {
        dao.deleteById(userid);
    }

    public S_user updateByEntity(S_user s_user) {
        return dao.save(s_user);
    }
}
