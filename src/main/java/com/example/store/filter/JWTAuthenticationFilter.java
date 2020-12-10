package com.example.store.filter;
import com.example.store.common.Result;
import com.example.store.common.ResultStatus;
import com.example.store.util.JwtTokenUtils;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * Token的应用：请求头里加Authorization，并加上Bearer标注
 */


/**
 * 用于执行鉴权工作的过滤器
 * 对于每个请求都尝试从请求头中取出token并验证
 * 若token有效则托管给SpringSecurity进行进一步、细粒度的权限控制
 */

public class JWTAuthenticationFilter extends BasicAuthenticationFilter {

    @Resource
    private UserDetailsService userDetailsService;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        //获取token
        String token = Optional.ofNullable(request.getHeader("Authorization"))
                                .map(s -> s.replace("Bearer ", ""))    //s指的是Optional的实例值，也就是Header->"Authorization"
                                .orElse(null);

        //若请求头中没有有效的token，则过滤
        try{
            if (StringUtils.isEmpty(token)) {
                Result result = new Result().setResultStatus(ResultStatus.UNAUTHORIZED)
                .setMessage("Access Denied");

                response.setContentType("application/json; charset=utf-8");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(result.toString());

                return;
            }
            //token过期
            if (JwtTokenUtils.IsExpiration(token)){
                SecurityContextHolder.clearContext();
                super.doFilterInternal(request,response,chain);
                return;
            }
        }catch (ExpiredJwtException e){
            SecurityContextHolder.clearContext();
            responseExpiredMessage(response);
            return;
        }

        //请求头中token有效，解析并设置凭证信息
        SecurityContextHolder.getContext().setAuthentication(getAuthentication(token));
        super.doFilterInternal(request,response,chain);
    }

    //token过期的操作
    private void responseExpiredMessage(HttpServletResponse response) throws IOException {
        Result result = new Result().setResultStatus(ResultStatus.TOKEN_EXPIRED)
                .setMessage(ResultStatus.TOKEN_EXPIRED.getStatus());

        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(result.toString());
    }


    /**
     * 解析token字符串，获取认证信息,也就是赋予权限
     */
    private Authentication getAuthentication(String token) {
        String username = JwtTokenUtils.getUserName(token);

        //提取角色信息
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if (userDetails != null) {

            //创建一个UsernamePasswordAuthenticationToken放到当前的Context，然后继续执行过滤连请求继续执行下去
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities());

            authenticationToken.setDetails(userDetails);

            return authenticationToken;
        }
        return null;
    }

}
