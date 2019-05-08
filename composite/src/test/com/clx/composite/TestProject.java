package com.clx.composite;

import com.clx.composite.model.UserDO;
import com.clx.composite.utils.RedisUtil;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestProject {
    @Test
    public void pro(){
        ApplicationContext app=new ClassPathXmlApplicationContext("classpath*:applicationContext.xml");
        RedisUtil redisUtil = (RedisUtil) app.getBean("redisUtil");

        redisUtil.getStringRedisTemplate().opsForValue().set("email","66666");
    }
}
