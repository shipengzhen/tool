package com.spz.tools.tool.proxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibProxy implements MethodInterceptor {

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy)
                    throws Throwable {
        Object result = null;
        if ("red".equals(type)) {
            result = method.invoke(new Red(), args);
        } else if ("blue".equals(type)) {
            result = method.invoke(new Blue(), args);
        } else {
            //通过代理类调用父类中的方法
            result = methodProxy.invokeSuper(proxy, args);
        }
        return result;
    }

    public Object createProxy(Class<?> clazz) {
        Enhancer enhancer = new Enhancer();
        //设置需要创建子类的类
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        //通过字节码技术动态创建子类实例
        return enhancer.create();
    }

    public static void main(String[] args) {
        CglibProxy cglibProxy = new CglibProxy();
        cglibProxy.setType("blue");
        Color color = (Color) cglibProxy.createProxy(Color.class);
        color.find();
    }

}
