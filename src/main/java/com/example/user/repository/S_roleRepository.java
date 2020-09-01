package com.example.user.repository;

import com.example.user.entity.S_role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @Author ZhangQiangLong
 * @Date 2020/7/30 16:37
 * @Text:
 **/
@Component
public interface S_roleRepository extends JpaRepository<S_role,String> {
    S_role findFirstByRolename(String rolename);
}
