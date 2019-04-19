package com.lhch.fun.annotation;

/**
 * @author linhaicheng on 2019/04/13
 */
public abstract class AbstractPersonImpl implements IPerson {

    protected abstract void hate();

    public void play() {
        System.out.println(" i love 极品飞车");
    }

    // @Override 注解是对接口的实现
    @Override
    public void love() {
        System.out.println("i love java");
    }

}
