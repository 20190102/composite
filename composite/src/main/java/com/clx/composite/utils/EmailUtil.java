package com.clx.composite.utils;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailUtil {

    public static void send(String emailTo, String subject, String Text) throws MessagingException {
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

        long start=System.currentTimeMillis();
        try {
            //发件人
            message.setFrom(new InternetAddress("x_201904@sina.com"));
            //收件人
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
            //主题
            message.setSubject(subject);
            //内容
            message.setText(Text);
            //发送邮件
            Transport.send(message);
        } catch (MessagingException e) {
            System.out.println("写入错误文件");
            throw new MessagingException("服务器错误");
        }

        long end=System.currentTimeMillis();
        System.out.println("发送邮件耗时："+(end-start)+"这也太慢了，难受");
    }
}
