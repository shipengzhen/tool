package com.spz.tools.tool.proxy;

public class Proxy implements Color {

    public Proxy() {
        super();
    }

    public Proxy(Color color) {
        this.color = color;
    }

    private Color color;

    @Override
    public void find() {
        color.find();
    }

    public static void main(String[] args) {
        //TargetInterface targetInterface = new TargetImpl();
        //targetInterface.find();

        Color target = new Red();
        Color proxy = new Proxy(target);
        proxy.find();

    }
}
