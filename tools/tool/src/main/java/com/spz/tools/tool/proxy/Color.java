package com.spz.tools.tool.proxy;

public interface Color {

    public default void find() {
        System.out.println("我是-->" + Color.class);
    }
}
