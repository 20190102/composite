package com.clx.composite.utils;

import com.clx.composite.exception.DataInvalidException;

/**
 * 数据格式校验工具类
 */
public class CheckUtil {

    /**
     * 校验邮箱格式
     *
     * @return
     */
    public static void checkEmail(String email) throws DataInvalidException {
        String reg = "^[0-9a-zA-Z-_]+@[a-zA-Z]+\\.[a-zA-Z]{2,5}(\\.[a-zA-Z]{2,5}){0,1}$";
        if (email == null || !email.matches(reg)) {
            throw new DataInvalidException("邮箱格式错误");
        }
    }

    /**
     * 校验密码格式
     *
     * @param password
     * @return
     */
    public static void checkPassword(String password) throws DataInvalidException {
        String reg = "^.{6,16}$";
        if (reg == null || !password.matches(reg)) {
            throw new DataInvalidException("密码格式错误");
        }
    }

    /**
     * 校验用户名格式
     *
     * @param username
     * @return
     */
    public static void checkUsername(String username) throws DataInvalidException {
        String reg = "^[0-9a-zA-Z-_@\\.\\u4e00-\\u9fa5]{3,12}";
        if (username == null || !username.matches(reg)) {
            throw new DataInvalidException("用户名格式错误");
        }
    }

    /**
     * 校验日期格式
     *
     * @param date
     * @return
     */
    public static void checkDate(String date) throws DataInvalidException {

        if (date == null) return;

        String reg = "([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8])))";
        if (!date.matches(reg)) {
            throw new DataInvalidException("生日格式错误");
        }
    }

    /**
     * 验证码格式校验
     *
     * @param code
     * @throws DataInvalidException
     */
    public static void checkCode(String code) throws DataInvalidException {
        String reg = "^[0-9]{6}$";
        if (code == null || !code.matches(reg)) {
            throw new DataInvalidException("验证码格式错误");
        }
    }

    /**
     * 校验性别参数
     *
     * @param sex
     * @throws DataInvalidException
     */
    public static void checkSex(String sex) throws DataInvalidException {
        String reg = "^[123]$";
        if (sex == null || !sex.matches(reg)) {
            throw new DataInvalidException("无效的性别参数");
        }
    }

    /**
     * 校验权限参数
     *
     * @param permission
     * @throws DataInvalidException
     */
    public static void checkPermission(String permission) throws DataInvalidException {
        String reg = "^[123]$";
        if (permission == null || !permission.matches(reg)) {
            throw new DataInvalidException("无效的权限参数");
        }
    }

}
