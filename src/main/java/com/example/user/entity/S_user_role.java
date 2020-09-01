package com.example.user.entity;

import com.example.user.primarykey.S_user_rolePrimaryKey;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * @Author ZhangQiangLong
 * @Date 2020/7/2 9:20
 * @Text: spring jpa设置联合主键的时候，必须要在类声名注入@IdClass(XXXPK.class)。
 * EntityListeners 监听，可以监听实体变化情况，可自行实现监听器
 **/
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "S_USER_ROLE")
/*@IdClass(S_user_rolePrimaryKey.class)*/
public class S_user_role extends UserBaseEntity {

    public S_user_role() {
    }
    @Id
    /**随机ID生成规则*/
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
    @Column(name = "systemid")
    private String systemid;

    @Column(name = "user_id")
    private String userid;

    @Column(name = "role_id")
    private String roleid;

}
