package com.clx.composite.utils;

import java.util.regex.Pattern;

/**
 * 从页面接收的数据有效性验证
 */
public class CheckUtil {
    private static Pattern pattern;
    /**
     * 验证邮箱
     * @return
     */
    public static boolean checkEmail(String email){
        if(email==null)return false;
        String reg="^[0-9a-zA-Z-_]+@[a-zA-Z]+\\.[a-zA-Z]{2,5}(\\.[a-zA-Z]{2,5}){0,1}$";
        return  email.matches(reg);
    }

    /**
     * 校验密码
     * @param password
     * @return
     */
    public static boolean checkPassword(String password){
        if(password==null)return false;
        String reg="^.{6,16}$";
        return password.matches(reg);
    }

    /**
     * 校验用户名
     * @param username
     * @return
     */
    public static boolean checkUsername(String username){
        if(username==null)return false;
        String reg="^[0-9a-zA-Z-_@\\.\\u4e00-\\u9fa5]{3,12}";
        return username.matches(reg);
    }

    /**
     * 校验日期
     * @param date
     * @return
     */
    public static boolean checkDate(String date){
        if(date==null)return false;
        String reg="([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8])))";
        return date.matches(reg);
    }


    public static boolean checkEmailAndPwd(String email,String password){
        return checkEmail(email)&&checkPassword(password);
    }
}
