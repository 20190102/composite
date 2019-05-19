package com.clx.composite.utils;



import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * jwt工具类
 */
public class JwtUtil {
    //密钥在redis中的key
    private static final String REDIS_KEY = "secretKey";

    /**
     * 创建密钥，保存在redis，有效期三天
     *
     * @return SecretKey
     */
    public static SecretKey getSecretKey(RedisUtil redisUtil) {

        String jwtKey = null;
        if (redisUtil.exists(REDIS_KEY)) {
            jwtKey = redisUtil.get(REDIS_KEY).toString();
        } else {
            jwtKey = UUID.randomUUID().toString();
            redisUtil.set(REDIS_KEY, jwtKey, 3, TimeUnit.DAYS);
        }
        byte[] secretKey = jwtKey.getBytes();
        return new SecretKeySpec(secretKey, "AES");
    }

    /**
     * 创建token
     *
     * @param date      过期时间
     * @param secretKey 密钥
     * @return
     */
    public static String getToken(Date date, SecretKey secretKey,boolean remember) {

        //生成构造器
        JwtBuilder jwt =
                Jwts.builder()
                        //编号，唯一字符
                        .setId(UUID.randomUUID().toString())
                        .claim("remember",remember)
                        //签发人
                        .setIssuer("clx.com")
                        //签发时间
                        .setIssuedAt(new Date())
                        //过期时间
                        .setExpiration(date)
                        //指定签名算法和密钥
                        .signWith(SignatureAlgorithm.HS256, secretKey);

        //token字符串
        return jwt.compact();
    }

    /**
     * 解析token
     *
     * @param token
     * @return
     * @throws Exception
     */
    public static Claims parser(String token, SecretKey secretKey) throws Exception {
        Claims claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();

        return claims;
    }
}
