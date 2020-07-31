package com.example.user.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

/**
 * @Author ZhangQiangLong
 * @Date 2020/7/2 9:20
 * @Text:
 **/

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "S_USER")
public class S_user extends UserBaseEntity{

    public S_user() {
    }

    @Id
    /**随机ID生成规则*/
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
    @Column(name = "user_id")
    private String userid;
    @Column(name = "user_name")
    private String username;
    @Column(name = "user_password")
    private String password;
    private String sex;
    /**
     * @Transient 表明是临时字段,roles是该用户的角色列表
     */
    @Transient
    private List<String> roles;
}
