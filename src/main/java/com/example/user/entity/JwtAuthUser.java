package com.example.user.entity;

import com.example.user.entity.S_user;
import com.example.user.filter.JwtPreAuthFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * @Author ZhangQiangLong
 * @Date 2020/8/14 15:08
 * @Text: JwtAuthUser类实现了UserDetails，从而封装了用户信息，用于认证和鉴权
 *
 **/
public class JwtAuthUser implements UserDetails {

    private static final Logger logger = LoggerFactory.getLogger(JwtPreAuthFilter.class);

    private String userid;
    private String userName;
    private String password;
    private List<String> roles;
    private Collection<? extends GrantedAuthority> authorities;

    /**
     * description: 通过FXUser来创建JwtAuthUser
     *
     * @param user
     * @return
     */
    public JwtAuthUser(S_user user){
        this.userid=user.getUserid();
        this.userName=user.getUsername();
        this.password=user.getPassword();
        this.roles=user.getRoles();
    }

    /**
     * description: 鉴权最重要方法，通过此方法来返回用户权限
     *
     * @param
     * @return java.util.Collection<? extends org.springframework.security.core.GrantedAuthority>
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new HashSet<>();
        if (roles!=null) {
            for (String role : roles) {
                authorities.add(new SimpleGrantedAuthority(role));
            }
        }
        logger.info("authorities:{}",authorities);
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "JwtAuthUser{" +
                "userid=" + userid +
                ", username='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", authorities=" + roles +
                '}';
    }
}