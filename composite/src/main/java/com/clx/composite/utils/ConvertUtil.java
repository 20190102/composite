package com.clx.composite.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 数据转换工具类
 */
public class ConvertUtil {
    private static  ObjectMapper objectMapper=new ObjectMapper();

    /**
     * 计算time秒以后的时间
     * @param time
     * @return
     */
    public static Date addTimeToDate(int time){
        long t=System.currentTimeMillis()+time*1000;
        return new Date(t);
    }

    /**
     * 格式化时间
     * @param date
     * @return
     */
    public static String dataFormat(Date date){
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        return  dateFormat.format(date);
    }

    /**
     * 对象转json
     * @param t
     * @param <T>
     * @return
     */
    public  static <T> String objectToJson(T t)   {
        String json= null;
        try {
            json = objectMapper.writeValueAsString(t);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static <T> T jsonToObject(String json,Class<T> t) throws IOException {
        Object object=objectMapper.readValue(json,t);
        return object.getClass()==t?(T)object:null;
    }

}
