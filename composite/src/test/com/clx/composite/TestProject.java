package com.clx.composite;

import com.clx.composite.utils.JwtUtil;
import com.clx.composite.utils.RedisUtil;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class TestProject {

    public SecretKey getSecretKey() {

        byte[] secretKey = "666".getBytes();
        return new SecretKeySpec(secretKey, "AES");
    }

    public Date secondToDate(int time) {
        long t = System.currentTimeMillis() + time * 1000;
        return new Date(t);
    }


    public void createToken() {

        //生成构造器
        JwtBuilder jwt =
                Jwts.builder()
                        //编号，唯一字符
                        .setId(UUID.randomUUID().toString())
                        //设置头信息
                        .setHeaderParam("type", "JWT")
                        //签发人
                        .setIssuer("clx")
                        //受众
                        .setAudience("666")
                        //签发时间
                        .setIssuedAt(new Date())
                        //生效时间
                        .setNotBefore(new Date())
                        //过期时间
                        .setExpiration(secondToDate(100))
                        //指定签名算法和密钥
                        .signWith(SignatureAlgorithm.HS256, getSecretKey());


        System.out.println(jwt.compact());
    }


    public void testArray() {
        List<Object> list = new ArrayList<Object>();
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }
        System.out.println(list.subList(10, list.size()).toString());
    }

    public SecretKey getKey() {

        String jwtKey = UUID.randomUUID().toString();

        byte[] secretKey = jwtKey.getBytes();
        return new SecretKeySpec(secretKey, "AES");
    }

    @Test
    public void testredis() {

        List<String> list=new ArrayList<>();
        list.add("a");
        list.add("b");
        rightPushAll("a",list);

    }
    public <K,V> void rightPushAll(K key,List<V> value){
        ApplicationContext app = new ClassPathXmlApplicationContext("classpath*:applicationContext.xml");
        RedisTemplate r = (RedisTemplate) app.getBean("redisTemplate");
        r.opsForList().rightPushAll(key,value);
    }


    public void sendEamil() throws MessagingException {
        //获取系统属性
        Properties properties = System.getProperties();
        //设置smtp服务器地址
        properties.setProperty("mail.smtp.host", "smtp.sina.com");
        //打开身份验证
        properties.setProperty("mail.smtp.auth", "true");
        /*
            根据不同邮箱的策略设置属性
            属性字段可查看https://javaee.github.io/javamail/docs/api/overview-summary.html
        */

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("x_201904@sina.com", "7Bt7YD");
            }
        });

        //创建邮件对象
        MimeMessage message = new MimeMessage(session);
        InternetAddress addressFrom = null;
        InternetAddress addressTo = null;


        //创建目的邮箱地址
        addressTo = new InternetAddress("asoifujpa@dg.com");


        //创建发送邮箱地址
        addressFrom = new InternetAddress("x_201904@sina.com");
        //发件人
        message.setFrom(addressFrom);
        //收件人
        message.addRecipient(Message.RecipientType.TO, addressTo);
        //主题
        message.setSubject("sdfa");
        //内容
        message.setText("sdf");
        //发送邮件
        Transport.send(message);
    }

}
