package com.example.user.repository;

import com.example.user.entity.S_user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author ZhangQiangLong
 * @Date 2020/7/2 11:54
 * @Text:
 **/
@Component
public interface S_userRepository extends JpaRepository<S_user, String> {
    S_user findByUserid(String userid);
    S_user findByUsername(String username);
    @Query(nativeQuery = true,value ="SELECT ROLE_NAME from s_role WHERE ROLE_ID in (select ROLE_ID from s_user_role where USER_ID = (select USER_ID from s_user where USER_NAME= ?1));")
    List<String> getRolesByUsername(String userName);

}
