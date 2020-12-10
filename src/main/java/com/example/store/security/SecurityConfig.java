package com.example.store.security;

import com.example.store.filter.JWTAuthenticationFilter;
import com.example.store.filter.LoginFilter;
import com.example.store.service.impl.BgUserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)  //允许方法级别的权限控制
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private BgUserService userService;

    private LoginFilter loginFilter() throws Exception {
        return new LoginFilter(super.authenticationManager());
    }

    @SneakyThrows
    private JWTAuthenticationFilter jwtAuthenticationFilter(){
        return new JWTAuthenticationFilter(super.authenticationManager());
    }

    //注入即可使用此种加密方式
    @Bean
    PasswordEncoder passwordEncoder() {                      //密码加密方式
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //配置认证方式

        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/verifyCode");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http相关的配置，包括登陆登出、异常处理、会话管理等
//        http.authorizeRequests()        //不拦截POST请求发的预请求：options请求
//                .antMatchers(HttpMethod.OPTIONS,"/**").permitAll().and()
            http.csrf().disable()
                    .cors()                 //开启由于加入springsecurity出现跨域问题
                    .and()
                    .csrf().disable()       //取消SpringSecurity自登陆验证,以及API POST工具的测试，不开启时，工具报403
                    .addFilter(loginFilter())
                    .addFilter(jwtAuthenticationFilter());
    }
}
