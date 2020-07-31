package com.example.user.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * @Author ZhangQiangLong
 * @Date 2020/7/2 9:20
 * @Text:
 **/

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "S_ROLE")
public class S_role extends UserBaseEntity{

    public S_role() {
    }

    @Id
    /**随机ID生成规则*/
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
    @Column(name = "role_id")
    private String roleid;
    @Column(name = "role_name")
    private String rolename;
}
