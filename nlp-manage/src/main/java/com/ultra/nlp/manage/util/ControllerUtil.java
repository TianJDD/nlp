package com.ultra.nlp.manage.util;

import java.lang.reflect.Member;
import java.lang.reflect.Method;

import com.ultra.nlp.manage.controller.SolutionController;
import com.ultra.nlp.manage.model.JsonModel;
import com.ultra.nlp.manage.model.ReturnCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 功能描述:
 *  controller层的日志打印以及异常捕获
 * @param:
 * @return:
 * @auther: guyuefei
 * @date:
 */
public class ControllerUtil {
    private static Logger logger = LoggerFactory.getLogger(ControllerUtil.class);
    //用于拼接字符串
    private static final StringBuffer buffer = new StringBuffer();
    // 用于返回json格式数据
    private static final JsonModel json = null;
    //用于传参
    private static final Map map = new HashMap<String, Object>();

    public static void logger(String params ,String ... vaiues) {
        String s = "传入参数keyword为：";
        buffer.append(s);
        String[] ps = params.split(",");
        for(int n = 0 ; n < params.length();n ++){
            if(n == params.length() - 1)
                buffer.append(ps[n] + "=" + vaiues[n]);
            else
                buffer.append(ps[n] + "=" + vaiues[n]+",");
        }
        logger.info(buffer.toString());
    }

   public static Object selectTryCatchException(Class classname,String methodName) {
        try {
            //Method method = classname.getDeclaredMethod(methodName);
            Method[] methods = classname.getMethods();

            for (Method method : methods) {
                if(methodName.equals(method.getName())){
                    Map<Member,String[]> map  =  new ConcurrentHashMap<>(32);
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    Parameter[] paras = method.getParameters();
                    for(int n = 0 ; n < parameterTypes.length ;n  ++){
                        String parameterName = parameterTypes[n].getName();
                        System.out.println("参数类型:" + parameterName);
                        String paa = paras[n].getName();
                        System.out.println("参数名称:" + paa);
                        System.out.println("*****************************");
                    }

                }
            }
           /* Parameter[] paras  = method.getParameters();
            for(Parameter para : paras){
                System.out.println(para.toString());
            }*/
            JsonModel json = new JsonModel(true, ReturnCode.SUCESS_CODE_0000.getValue(), ReturnCode.SUCESS_CODE_0000.getKey(),map);
            return json;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            JsonModel json = new JsonModel(true, ReturnCode.ERROR_CODE_11001.getValue(), ReturnCode.ERROR_CODE_11001.getKey(), null);
            return json;
        }
    }


    public static void main(String[] args) {
        ControllerUtil.selectTryCatchException(SolutionController.class,"detail");
    }
}
