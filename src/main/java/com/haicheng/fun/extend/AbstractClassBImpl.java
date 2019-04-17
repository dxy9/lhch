package com.haicheng.fun.extend;

/**
 * @author linhaicheng on 2019/04/14
 */
public class AbstractClassBImpl extends AbstractClassB implements InterfaceA {

    public static void main(String[] args) {
        /*
        接口和抽象类中定义有同名的变量的时候
        子类会报错
         */
        System.out.println(new AbstractClassBImpl().x);

    }

}
