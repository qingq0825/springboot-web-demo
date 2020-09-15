package com.bm.commont.util;

import com.auth0.jwt.exceptions.JWTCreationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 分发token和验证token
 *
 * @author qinguoqing
 * @date 2020年4月1日 上午11:38:01
 */
@Slf4j
public class JwtHelper {

    /**
     * token过期时间目前暂时是30分钟
     */
    private static final long EXPIRE_TIME = 30L * 60 * 1000;
    /**
     * 公共密钥
     */
    private static final String TOKEN_SECRET = "lkjasdklhwjqjb";

    /**
     * 根据传参生成token
     *
     * @param account 账号
     * @param userId  用户ID
     * @return
     */
    public static String getToken(String account, String userId) {
        String token = null;
        try {
            SecretKey key = new SecretKeySpec(TOKEN_SECRET.getBytes(), "AES");
            // 过期时间
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            // 设置头部信息
            Map<String, Object> header = new HashMap<>(2);
            header.put("typ", "JWT");
            header.put("alg", "HS256");
            token = Jwts.builder()
                    .claim("account", account)
                    .claim("userId", userId)
                    .setExpiration(date)
                    .signWith(SignatureAlgorithm.HS256, key)
                    .compact();
        } catch (JWTCreationException | IllegalArgumentException ex) {
            ex.printStackTrace();
        }
        return token;
    }

    /**
     * 生成token
     *
     * @return
     */
    public static String getToken() {
        String token = null;
        try {
            SecretKey key = new SecretKeySpec(TOKEN_SECRET.getBytes(), "AES");
            // 过期时间
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            // 设置头部信息
            HashMap<String, Object> header = new HashMap<>(2);
            header.put("typ", "JWT");
            header.put("alg", "HS256");
            token = Jwts.builder()
                    .setExpiration(date)
                    .signWith(SignatureAlgorithm.HS256, key)
                    .compact();
        } catch (JWTCreationException | IllegalArgumentException ex) {
            ex.printStackTrace();
        }
        return token;
    }

    /**
     * 校验token中的参数是否正确
     *
     * @param token
     * @return
     */
    public static boolean verify(String token) {
        try {
            SecretKey key = new SecretKeySpec(TOKEN_SECRET.getBytes(), "AES");
            Claims claims = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token)
                    .getBody();
            log.info("解密信息:" + claims);
            return true;
        } catch (Exception e) {
            log.error("不能相信签名:" + token);
            return false;
        }
    }

    public static void main(String[] args) {
        String token = getToken();
        System.out.println(token);
        boolean verify = verify(token);
        System.out.println(verify);
        System.out.println("================================");

        String token1 = getToken("zs","123");
        System.out.println(token1);
        boolean verify1 = verify(token1);
        System.out.println(verify1);
    }

}