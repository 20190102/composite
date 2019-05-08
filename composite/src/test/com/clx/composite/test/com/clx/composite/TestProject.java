package com.clx.composite.test.com.clx.composite;

import com.clx.composite.model.UserDO;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestProject {
    @Test
    public void pro(){
        ApplicationContext app=new ClassPathXmlApplicationContext("classpath*:applicationContext.xml");
        UserDO userDO =(UserDO)app.getBean("userBean");
        System.out.println(userDO);

    }
}
