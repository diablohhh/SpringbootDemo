package com.example.user.server;

import com.example.user.entity.S_role;
import com.example.user.entity.S_user;
import com.example.user.entity.S_user_role;
import com.example.user.repository.S_roleRepository;
import com.example.user.repository.S_userRepository;
import com.example.user.repository.S_user_roleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private S_user_roleServer s_user_roleServer;
    @Autowired
    private S_roleServer s_roleServer;


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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
        s_user.setPassword(bCryptPasswordEncoder.encode(s_user.getPassword()));
        List<String> roles = new ArrayList<>();
        //基础角色ROLE_USER
        String roleName = "ROLE_USER";
        roles.add(roleName);
        s_user.setRoles(roles);
        S_user result = dao.save(s_user);
        S_role s_role = s_roleServer.getRoleByRoleName(roleName);
        if(null!=result && null!=s_role){
        // 插入用户成功时生成用户的角色信息
            String userid=result.getUserid();
            String roleid = s_role.getRoleid();
            S_user_role s_user_role = new S_user_role();
            s_user_role.setRoleid(roleid);
            s_user_role.setUserid(userid);
            s_user_roleServer.saveByEntity(s_user_role);
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
