package com.example.user.filter;

import com.example.user.utils.JwtTokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * @Author ZhangQiangLong
 * @Date 2020/8/14 15:40
 * @description: 对所有请求进行过滤
 * BasicAuthenticationFilter继承于OncePerRequestFilter==》确保在一次请求只通过一次filter，而不需要重复执行。
 * 在JwtLoginAuthFilter中我们对登录的用户进行认证，认证成功时将生成Token给用户前端，之后前端保存该Token在Cookie或session中，当用户访问服务器时，只需携带该Token即可访问服务。而Token的认证鉴权工作就是由本例中的JwtPreAuthFilter来实现了。
 *
 * JwtPreAuthFilter继承了BasicAuthenticationFilter，该过滤器是继承于OncePerRequestFilter，用于确保在一次请求只通过一次filter，而不需要重复执行。简单的说，就是用户的每次请求都将经过该过滤器。下面我们详细看看该过滤器中的代码。
 *
 * doFilterInternal方法从request的header中查看是否带有Token，如果没有则放行，如果有则进行Token解析（调用getAuthentication方法），并设置认证信息。
 */
public class JwtPreAuthFilter extends BasicAuthenticationFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtPreAuthFilter.class);

    public JwtPreAuthFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    /**
     * description: 从request的header部分读取Token
     *
     * @param request
     * @param response
     * @param chain
     * @return void
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        String tokenHeader = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
        logger.info("BasicAuthenticationFilters-tokenHeader:{}",tokenHeader);
        // 如果请求头中没有Authorization信息则直接放行了
        if (tokenHeader == null || !tokenHeader.startsWith(JwtTokenUtils.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        // 如果请求头中有token，则进行解析，并且设置认证信息
        SecurityContextHolder.getContext().setAuthentication(getAuthentication(tokenHeader));
        super.doFilterInternal(request, response, chain);
    }

    /**
     * description: 读取Token信息，创建UsernamePasswordAuthenticationToken对象
     *
     * @param tokenHeader
     * @return org.springframework.security.authentication.UsernamePasswordAuthenticationToken
     */
    private UsernamePasswordAuthenticationToken getAuthentication(String tokenHeader) {
        //解析Token时将“Bearer ”前缀去掉
        String token = tokenHeader.replace(JwtTokenUtils.TOKEN_PREFIX, "");
        String username = JwtTokenUtils.getUsername(token);
        List<String> roles = JwtTokenUtils.getUserRole(token);
        Collection<GrantedAuthority> authorities = new HashSet<>();
        if (roles!=null) {
            for (String role : roles) {
                authorities.add(new SimpleGrantedAuthority(role));
            }
        }
        if (username != null){
            return new UsernamePasswordAuthenticationToken(username, null, authorities);
        }
        return null;
    }
}
