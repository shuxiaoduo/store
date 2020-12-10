package com.example.store.util;


import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTokenUtils {

    /**
     * 公用密钥-用来数据的签名和解签，存放在服务端的,可随意指定
     */
    //密钥不能太短，太短报错secret key byte array cannot be null or empty
    private static final String SECRET = "**SXD0619**";

    private static final int EXPIRATION_TIME = 60 * 60;  //token的有效时间=>s

    /**
     * jwt的签发者
     */
    private static final String ISSUE = "shuxiaoduo";

    /**
     * 获得token过期时间
     *
     * @return
     */
    private static Date getExpiration() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, EXPIRATION_TIME);
        return calendar.getTime();
    }

    /**
     * 通过用户中获取声明
     *
     * @return
     */
    private static Claims getClaims(UserDetails userDetails) {
        DefaultClaims claims = new DefaultClaims();
        claims.setIssuedAt(new Date())
                .setExpiration(getExpiration())
                .setIssuer(ISSUE)
                .setAudience(userDetails.getUsername());     //根据用户名生成token
        return claims;

    }

    private static String generateToken(Map<String,Object> header, Claims claims){
        String alg = (String) header.get("alg");

        return Jwts.builder()
                .setHeader(header)
                .setClaims(claims)
                .signWith(SignatureAlgorithm.valueOf(alg), SECRET)
                .compact();
    }

    /**
     *通过用户获取jwt
     * @return
     */
    public static String generateToken(UserDetails userDetails){
        return generateToken(
                new HashMap<String, Object>() {{
                    put("typ", "JWT");
                    put("alg", "HS256");
                }},
                getClaims(userDetails)
        );
    }

    /**
     * 根据token获取username
     * @param token
     * @return
     */
    public static String getUserName(String token){
        try {
            Jws<Claims> jwt = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return jwt.getBody().getAudience();    //payload中的aud（token的客户）
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 是否过期
     * @param token
     * @return
     */
    public static boolean IsExpiration(String token){
        try {
            Jws<Claims> jwt = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return generateToken(jwt.getHeader(), jwt.getBody()).equals(token);
        } catch (ExpiredJwtException e) {
            throw e;
        } catch (Exception e) {
            return false;
        }
    }

}
