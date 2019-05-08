package com.clx.composite.utils;



import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 验证码的创建，发送，校验
 */
public class CaptchaUtil {
    /**
     * 发送邮件
     * @param email
     * @param subject
     * @param Text
     * @return
     */
    public static boolean sendMail(String email,String subject,String Text) {
        //获取系统属性
        Properties properties=System.getProperties();
        //设置smtp服务器地址
        properties.setProperty("mail.smtp.host","smtp.sina.com");
        //打开身份验证
        properties.setProperty("mail.smtp.auth","true");
        /*
            根据不同邮箱的策略设置属性
            属性字段可查看https://javaee.github.io/javamail/docs/api/overview-summary.html
        */

        Session session=Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("x_201904@sina.com","7Bt7YD");
            }
        });
        try {
            //创建邮件对象
            MimeMessage message=new MimeMessage(session);
            //发件人
            message.setFrom(new InternetAddress("x_201904@sina.com"));
            //收件人
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(email));
            //主题
            message.setSubject(subject);
            //内容
            message.setText(Text);
            //发送邮件
            Transport.send(message);
        } catch (MessagingException e) {
            return false;
        }
        return true;
    }

    /**
     * 创建，保存，返回验证码，有效期五分钟
     * @param email
     * @return token
     */

    public static String createAndSaveCode(String email){
        //创建6个数字的字符串
        long random=new Random().nextLong();
        String verificationCode=String.valueOf(random).substring(1,7);

        Map<String,String> map=new HashMap<>();
        //发送邮件次数
        map.put("count","1");
        //验证码
        map.put("verificationCode",verificationCode);

        //向redis中保存数据
        RedisUtil.redisTemplate.opsForHash().putAll(email,map);
        //5分钟过期
        RedisUtil.redisTemplate.expire(email,60*5, TimeUnit.SECONDS);
        return verificationCode;
    }

    /**
     * 校验验证码有效性
     * @param email
     * @param verificationCode
     * @return
     */
    public static boolean validateCode(String email, String verificationCode){
        if(CheckUtil.checkEmail(email)&&verificationCode!=null){
            String vCode= (String) RedisUtil.redisTemplate.opsForHash().get(email,"verificationCode");
            return  verificationCode.equals(vCode);
        }
        return false;
    }
}
