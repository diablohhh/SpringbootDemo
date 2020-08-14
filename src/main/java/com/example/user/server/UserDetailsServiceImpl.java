package com.example.user.server;

import com.example.user.entity.JwtAuthUser;
import com.example.user.entity.S_user;
import com.example.user.filter.JwtPreAuthFilter;
import com.example.user.repository.S_userRepository;;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Author ZhangQiangLong
 * @Date 2020/8/14 15:12
 * @Text: UserDetailsServiceImpl实现了UserDetailsService，只有一个方法loadUserByUsername，通过查询用户名从数据库中加载用户信息（UserDetails），这里是JwtAuthUser。
 * @description: 实现UserDetailsService，从数据库中加载用户信息==》用户名、密码及角色名
 *
 *  Spring Security中进行身份验证的是AuthenticationManager接口，ProviderManager是它的一个默认实现，但它并不用来处理身份认证，
 *  而是委托给配置好的AuthenticationProvider，每个AuthenticationProvider会轮流检查身份认证。检查后或者返回Authentication对象或者抛出异常。
 *
 *  验证身份就是加载响应的UserDetails，看看是否和用户输入的账号、密码、权限等信息匹配。
 *  此步骤由实现AuthenticationProvider的DaoAuthenticationProvider（它利用UserDetailsService验证用户名、密码和授权）处理。
 *  包含 GrantedAuthority 的 UserDetails对象在构建 Authentication对象时填入数据。
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(JwtPreAuthFilter.class);

    @Autowired
    private S_userRepository userRepository;

    /**
     * description: 通过用户名从数据库中读取该用户账户信息及权限信息
     *
     * @param userName 用户名
     * @return org.springframework.security.core.userdetails.UserDetails
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        S_user user = userRepository.findByUsername(userName);
        if(user==null){
            // 实际当用户不存在时，应该页面显示错误信息，并跳转到登录界面
            throw new UsernameNotFoundException("该用户不存在！");
        }
        user.setRoles(userRepository.getRolesByUsername(userName));
        logger.info("UserDetailsServiceImpl==>loadUserByUsername:{}",user);
        return new JwtAuthUser(user);
    }
}
