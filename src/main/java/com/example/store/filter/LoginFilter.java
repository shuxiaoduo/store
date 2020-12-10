package com.example.store.filter;

import com.example.store.common.Result;
import com.example.store.common.ResultStatus;
import com.example.store.entity.BgUser;
import com.example.store.entity.Vo.BgUserVo;
import com.example.store.util.JwtTokenUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * 登录过滤器,验证用户身份
 */
@Slf4j
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    /**
     * 身份认证器
     */
    private AuthenticationManager authenticationManager;

    public LoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        //定义拦截登录url
        super.setFilterProcessesUrl("/api/login");
    }

    /**
     * 接受并解析用户凭证
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     */
    @SneakyThrows
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        //登录请求方式必须为POST 否则抛出异常
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }
        //接下来通过 contentType 来判断当前请求是否通过 JSON 来传递参数，如果是通过 JSON 传递参数，
        //则按照 JSON 的方式解析，如果不是，则调用 super.attemptAuthentication 方法，
        //进入父类的处理逻辑中，也就是说，我们自定义的这个类，既支持 JSON 形式传递参数，也支持 key/value 形式传递参数
        // 从请求中获取用户名和密码（前端用body传参用这种）
        if (request.getContentType().contains(MediaType.APPLICATION_JSON_VALUE) || request.getContentType().contains(MediaType.APPLICATION_JSON_UTF8_VALUE)) {
            Map<String, String> loginData = new HashMap<>();
            try {
                loginData = new ObjectMapper().readValue(request.getInputStream(), Map.class);
            } catch (IOException e) {
            }
            String username = loginData.get(getUsernameParameter());
            String password = loginData.get(getPasswordParameter());
            System.out.println("用户："+username+" 密码："+password);
            if (username == null) {
                username = "";
            }
            if (password == null) {
                password = "";
            }
            username = username.trim();
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        } else {
            return super.attemptAuthentication(request, response);
        }
        // 从请求中获取用户名和密码（前端用param传参用这种）
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//
//        log.warn(username);
//
//        // 调用认证管理器来帮我们完成认证
//        // 在Spring Security内部实际上是调用了之前所传入了UserDetailsService的实例
//        // 通过用户名加载用户，然后进行匹配
//        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

    // 成功验证后调用的方法
    // 如果验证成功，就生成token并返回
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        //通过认证结果获取身份
        UserDetails principal =(UserDetails)authResult.getPrincipal();

        //通过身份生成token
        String token = JwtTokenUtils.generateToken(principal);

        // 以application/json格式响应
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");

        BgUserVo vo = new BgUserVo((BgUser) principal);
        vo.setToken(token);

        // 包装在自定义的统一响应结果：Result类 中
        Result result = new Result().success(vo).setMessage("登录成功！");

        // 通过response的输出流输出
        response.getWriter().write(result.toString());
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        Result result;

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        //账户锁定，证书失效等错误
        if(failed instanceof AuthenticationServiceException
                && failed.getMessage().startsWith("Authentication method not supported"))
        {
            response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            result = new Result().setStatus(ResultStatus.NOT_ALLOWED)
                                .setMessage(failed.getMessage());
        }
        //不细分用户名或者密码，提高安全性
        else{
            result = new Result().setStatus(ResultStatus.FORBIDDEN)
                                .setMessage("用户名或密码错误");
        }
        response.getWriter().write(result.toString());
    }

}
