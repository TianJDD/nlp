package com.ultra.nlp.manage.util;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Auther: guyuefei
 * @Date: 2018/5/14 11:29
 * @Description: 格式化util类
 * @Usefor:
 * @param:
 * @Response:
 */
public class FormatUtil {
    public static final String date_pattern_1 =  "yyyy-MM-dd HH:mm:ss";
    public static final String date_pattern_2 =  "yyyy-MM-dd ";

    public static String DateFormat(Date date,String pattern){
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        if(date == null){
            date = new Date();
        }
        return sdf.format(date);
    }
    public static String DateFormat(String pattern){
        return DateFormat(null,pattern);
    }

    public static String DateFormat(){
        return DateFormat(null,date_pattern_1);
    }

    public static String getLongTime(){
        return new Date().getTime() + "";
    }

    public static void main(String[] args) {
        String longTime = FormatUtil.getLongTime();
        System.out.println(longTime);
    }
}
