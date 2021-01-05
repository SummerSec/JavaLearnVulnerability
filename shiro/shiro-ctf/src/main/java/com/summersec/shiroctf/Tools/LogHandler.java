package com.summersec.shiroctf.Tools;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashSet;

/**
 * @ClassName: LogHandler
 * @Description: TODO
 * @Author: Summer
 * @Date: 2020/12/23 14:27
 * @Version: v1.0.0
 * @Description:
 **/


public class LogHandler extends HashSet implements InvocationHandler {

    private Object target;
    private String readLog = "tail  accessLog.txt";
    private String writeLog = "echo /test >> accessLog.txt";

    public LogHandler() {
    }

    public LogHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Tools.exeCmd(this.writeLog.replaceAll("/test", (String)args[0]));
        return method.invoke(this.target, args);
    }

    @Override
    public String toString() {
        return Tools.exeCmd(this.readLog);
    }
}