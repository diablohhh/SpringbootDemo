package com.example.user.filter;

import com.alibaba.fastjson.JSON;
import com.example.user.common.ResultEnum;
import com.example.user.common.ResultVO;
import com.example.user.controller.S_userController;
import com.example.user.entity.JwtAuthUser;
import com.example.user.entity.LoginUser;
import com.example.user.utils.JwtTokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * @Author ZhangQiangLong
 * @Date 2020/8/14 15:26
 * @description: 进行用户账号的验证==>认证功能
 *attemptAuthentication方法，该方法从登录界面读取到数据后，通过authenticationManager.authenticate方法，让SpringSecurity去进行验证，不需要自己查数据库对用户名和密码进行配对。
 *
 * attemptAuthentication方法进行校验后，有两种结果：成功或失败。当验证成功时将调用successfulAuthentication方法，失败调用unsuccessfulAuthentication方法。
 *
 * successfulAuthentication方法中首先通过Authentication.getPrincipal()方法来得到当前用户的信息（UserDetails），接着通过用户名、角色列表和是否记住我来构建Token。原本应该Token将放在response的header中的，但是设置header只能设置在当前页面的response中？
 *
 * unsuccessfulAuthentication方法是用户认证失败后返回信息。
 */
public class JwtLoginAuthFilter extends UsernamePasswordAuthenticationFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtLoginAuthFilter.class);

    private AuthenticationManager authenticationManager;

    private ThreadLocal<Boolean> rememberMe = new ThreadLocal<>();

    public JwtLoginAuthFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        // 设置该过滤器地址
        super.setFilterProcessesUrl("/auth/login");
    }

    /**
     * description: 登录验证
     *
     * @param request
     * @param response
     * @return org.springframework.security.core.Authentication
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        LoginUser loginUser = new LoginUser();
        loginUser.setUsername(request.getParameter("username"));
        loginUser.setPassword(request.getParameter("password"));
        loginUser.setRememberMe(Boolean.parseBoolean(request.getParameter("rememberMe")));
        logger.info("loginUser:{}",loginUser.toString());
        rememberMe.set(loginUser.getRememberMe());
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword(), new ArrayList<>())
        );

    }

    /**
     * description: 登录验证成功后调用，验证成功后将生成Token，并重定向到用户主页home
     * 与AuthenticationSuccessHandler作用相同
     *
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @return void
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        // 查看源代码会发现调用getPrincipal()方法会返回一个实现了`UserDetails`接口的对象，这里是JwtAuthUser
        JwtAuthUser jwtUser = (JwtAuthUser) authResult.getPrincipal();
        logger.info("JwtAuthUser:{}",jwtUser);
        boolean isRemember = rememberMe.get();
        List<String> roles = new ArrayList<>();
        Collection<? extends GrantedAuthority> authorities = jwtUser.getAuthorities();
        for (GrantedAuthority authority : authorities){
            roles.add(authority.getAuthority());
        }
        logger.info("roles:{}",roles);
        String token = JwtTokenUtils.createToken(jwtUser.getUsername(), roles,isRemember);
        logger.info("token:{}",token);
        // 重定向无法设置header,这里设置header只能设置到/auth/login界面的header
        //response.setHeader("token", JwtTokenUtils.TOKEN_PREFIX + token);

        // 登录成功重定向到home界面
        // 这里先采用参数传递
        response.sendRedirect("/home?token="+token);
    }

    /**
     * description: 登录验证失败后调用，这里直接Json返回，实际上可以重定向到错误界面等
     * 与AuthenticationFailureHandler作用相同
     *
     * @param request
     * @param response
     * @param failed
     * @return void
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().write(JSON.toJSONString(ResultVO.result(ResultEnum.USER_LOGIN_FAILED,false)));
    }
}