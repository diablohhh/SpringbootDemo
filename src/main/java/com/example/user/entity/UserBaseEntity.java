package com.example.user.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * @Author ZhangQiangLong
 * @Date 2020/7/30 16:09
 * @Text:
 **/
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class UserBaseEntity {
    /**
     * 创建时间，不能更新
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @CreatedDate
    @Column(name = "createdtime",updatable = false)
    private Date createdtime;

    /**
     * 最后更新时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @LastModifiedDate
    @Column(name = "lastupdatedtime")
    private Date lastupdatedtime;

    /**
     * 创建人，不能更新
     */
    /*@CreatedBy*/
    @Column(name = "creator",updatable = false)
    private String creator;

    /**
     * 最后更新人
     */
    /*@LastModifiedBy*/
    @Column(name = "lastupdatedby")
    private String lastupdatedby;
}
