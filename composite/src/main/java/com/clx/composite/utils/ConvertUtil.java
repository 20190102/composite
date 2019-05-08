package com.clx.composite.utils;

import java.util.Date;

/**
 * 数据转换工具类
 */
public class ConvertUtil {
    /**
     * 计算time秒以后的时间
     * @param time
     * @return
     */
    public static Date secondToDate(int time){
        long t=System.currentTimeMillis()+time*1000;
        return new Date(t);
    }
}
