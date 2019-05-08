package com.clx.composite.utils;

import io.jsonwebtoken.*;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * jwt的创建与校验
 */
public class JwtUtil {
    /**
     * 创建密钥，保存在redis，有效期三天
     * @return SecretKey
     */
    public static SecretKey getSecretKey(){

        String key=RedisUtil.stringRedisTemplate.opsForValue().get("secretKey");
        //如果secretKey这个key不存在，那么创建一个uuid存入secretKey这个key中
        if(key==null){
            key=UUID.randomUUID().toString();
            RedisUtil.stringRedisTemplate.opsForValue().set("secretKey",key,3, TimeUnit.DAYS);
        }
        byte[] secretKey=key.getBytes();
        return new SecretKeySpec(secretKey,"AES");
    }

    /**
     * 创建token
     * @param aud
     * @param date 过期时间
     * @return
     */
    public static String createToken(String aud,Date date){
        //生成构造器
        JwtBuilder jwt=
                Jwts.builder()
                    //编号，唯一字符
                    .setId(UUID.randomUUID().toString())
                    //设置头信息
                    .setHeaderParam("type","JWT")
                    //签发人
                    .setIssuer("clx")
                    //受众
                    .setAudience(aud)
                    //签发时间
                    .setIssuedAt(new Date())
                    //生效时间
                    .setNotBefore(new Date())
                    //过期时间
                    .setExpiration(date)
                    //指定签名算法和密钥
                    .signWith(SignatureAlgorithm.HS256,getSecretKey());

        //返回token字符串
        return jwt.compact();
    }

    /**
     * 解析token
     * @param token
     * @return 解析成功返回Claims，失败返回null
     */
    public static String parser(String token){
        Claims claims=null;
        try {
            claims= Jwts.parser().setSigningKey(getSecretKey()).parseClaimsJws(token).getBody();
            //如果过期时间-生效时间大于1天则视为被记住的用户，返回原token
            long t=claims.getExpiration().getTime()-claims.getNotBefore().getTime();
            if(t>1*24*60*60*1000L){
                return token;
            }
            return createToken(claims.getAudience(), ConvertUtil.secondToDate(60*30));
        }catch (Exception e){
            //如果能解析，返回token,不能解析，返回""
            return "";
        }
    }

}
