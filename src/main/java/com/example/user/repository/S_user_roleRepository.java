package com.example.user.repository;

import com.example.user.entity.S_role;
import com.example.user.entity.S_user_role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * @Author ZhangQiangLong
 * @Date 2020/7/30 16:41
 * @Text:
 **/
@Component
public interface S_user_roleRepository extends JpaRepository<S_user_role,S_user_role> {
    @Modifying
    @Query(nativeQuery = true,value = "INSERT INTO s_user_role(USER_ID,ROLE_ID) VALUES((SELECT USER_ID from s_user where USER_NAME=?1),(SELECT ROLE_ID FROM s_role WHERE ROLE_NAME=?2));")
    void saveEntity(String userName,String roleName);

}
