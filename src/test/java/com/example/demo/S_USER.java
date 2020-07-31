package com.example.demo;

import java.util.Date;

/**
 * @Author ZhangQiangLong
 * @Date 2020/7/2 9:20
 * @Text:
 **/
public class S_USER {
    private String userid;
    private String username;
    private String password;
    private Integer age;
    private String sex;
    private String zjzl;
    private String zjhm;
    private String sf;
    private String ryzt;
    private String bz;
    private String createdby;
    private Date createdtime;
    private String lastupdatedby;
    private String lastupdatedtime;
    private String deleteflag;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getZjzl() {
        return zjzl;
    }

    public void setZjzl(String zjzl) {
        this.zjzl = zjzl;
    }

    public String getZjhm() {
        return zjhm;
    }

    public void setZjhm(String zjhm) {
        this.zjhm = zjhm;
    }

    public String getSf() {
        return sf;
    }

    public void setSf(String sf) {
        this.sf = sf;
    }

    public String getRyzt() {
        return ryzt;
    }

    public void setRyzt(String ryzt) {
        this.ryzt = ryzt;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public Date getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    public String getLastupdatedby() {
        return lastupdatedby;
    }

    public void setLastupdatedby(String lastupdatedby) {
        this.lastupdatedby = lastupdatedby;
    }

    public String getLastupdatedtime() {
        return lastupdatedtime;
    }

    public void setLastupdatedtime(String lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }

    public String getDeleteflag() {
        return deleteflag;
    }

    public void setDeleteflag(String deleteflag) {
        this.deleteflag = deleteflag;
    }

    @Override
    public String toString() {
        return "s_user{" +
                "userid='" + userid + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", zjzl='" + zjzl + '\'' +
                ", zjhm='" + zjhm + '\'' +
                ", sf='" + sf + '\'' +
                ", ryzt='" + ryzt + '\'' +
                ", bz='" + bz + '\'' +
                ", createdby='" + createdby + '\'' +
                ", createdtime=" + createdtime +
                ", lastupdatedby='" + lastupdatedby + '\'' +
                ", lastupdatedtime='" + lastupdatedtime + '\'' +
                ", deleteflag='" + deleteflag + '\'' +
                '}';
    }
}
