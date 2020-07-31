package com.example.user.primarykey;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author ZhangQiangLong
 * @Date 2020/7/31 11:07
 * @Text:
 **/
@Data
public class S_user_rolePrimaryKey implements Serializable {
    private static final long serialVersionUID = 1L;
    private String userid;
    private String roleid;
}
