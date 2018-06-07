package com.ultra.nlp.manage.util;

/**
 * @Auther: admin
 * @Date: 2018/5/10 15:04
 * @Description:
 * @Usefor:
 * @param:
 * @Response:
 */
public class GenerateCode {

    /**
     * 功能描述:
     *生成三位code码
     * @param: n 传入需要生成的数字
     * @return:  三位code码字符串
     * @auther: guyuefei
     * @date:
     */
    public static String generateCode(int n){
        String code = "";
        if(n < 10){
            code = "00" + n;
        }else if (n < 100){
            code = "0" + n;
        }else{
            code = n + "";
        }
        return code;
    }

    /**
     * >1000
     * @param s
     * @return
     */
    public static String generateCode(int n,String s){
        String code = "";
        if(n > 1000){
            code = "00" + n;
        }else if (n < 100000){
            code = "0" + n;
        }else{
            code = n + "";
        }
        return code;
    }

    public static void main(String[] args) {
        String s = generateCode(1001,"2");
        System.out.println(s);
    }
}
