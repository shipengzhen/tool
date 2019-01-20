package com.spz.tools.tool.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxy implements InvocationHandler {

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        if ("red".equals(type)) {
            result = method.invoke(new Red(), args);
        } else if ("blue".equals(type)) {
            result = method.invoke(new Blue(), args);
        }
        return result;
    }

    public static void main(String[] args) {
        Color red = new Red();
        ClassLoader classLoader = red.getClass().getClassLoader();
        Class<?>[] interfaces = red.getClass().getInterfaces();
        JdkProxy jdkProxy = new JdkProxy();
        jdkProxy.setType("red");
        Color color = (Color) Proxy.newProxyInstance(classLoader, interfaces, jdkProxy);
        System.err.println(color);
        System.err.println(color.getClass());
        color.find();
    }

}
