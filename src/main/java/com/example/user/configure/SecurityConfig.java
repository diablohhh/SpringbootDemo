package com.example.user.configure;

import com.example.user.filter.JwtLoginAuthFilter;
import com.example.user.filter.JwtPreAuthFilter;
import com.example.user.handler.S_LogoutSuccessHandler;
import com.example.user.handler.UnAuthorizedEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * @Author ZhangQiangLong
 * @Date 2020/8/14 15:51
 * @description:
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    // 因为UserDetailsService的实现类实在太多啦，这里设置一下我们要注入的实现类
    @Qualifier("userDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    // 加密器
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * description: 加载userDetailsService，用于从数据库中取用户信息
     *
     * @param auth
     * @return void
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    /**
     * description: http细节
     *
     * @param http
     * @return 配置SpringSecurity，需要从细粒度到粗粒度。不然细粒度将不起作用。
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 开启跨域资源共享
        http.cors()
                .and()
                // 关闭csrf
                .csrf().disable()
                // 关闭session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .httpBasic().authenticationEntryPoint(new UnAuthorizedEntryPoint())
                .and()
                .authorizeRequests()
                // 需要角色为ADMIN才能删除该资源
                .antMatchers(HttpMethod.DELETE,"/tasks/**")
                .hasAnyRole("ROLE_ADMIN","ADMIN","ROLE_USER","USER")
                // 测试用资源，需要验证了的用户才能访问
                .antMatchers("/tasks/**").authenticated()
                // 其他都放行了
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                //.successHandler(new Fx)
                .and()
                .logout()//默认注销行为为logout
                .logoutSuccessHandler(new S_LogoutSuccessHandler())
                .and()
                // 添加到过滤链中
                // 先是UsernamePasswordAuthenticationFilter用于login校验
                .addFilter(new JwtLoginAuthFilter(authenticationManager()))
                // 再通过OncePerRequestFilter，对其他请求过滤
                .addFilter(new JwtPreAuthFilter(authenticationManager()));
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}