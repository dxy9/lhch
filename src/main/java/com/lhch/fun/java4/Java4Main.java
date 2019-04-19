package com.lhch.fun.java4;

/**
 * java4的新特性
 *
 * @author linhaicheng on 2019/04/19
 */
public class Java4Main {

    public static void main(String[] args) {
        fun();
    }

    /**
     * 断言
     * 1、assert <boolean表达式>
     * 如果<boolean表达式>为true，则程序继续执行。
     * 如果为false，则程序抛出AssertionError，并终止执行。
     * 2、assert <boolean表达式> : <错误信息表达式>
     * 如果<boolean表达式>为true，则程序继续执行。
     * 如果为false，则程序抛出java.lang.AssertionError，并输入<错误信息表达式>。
     * <p>
     * 使用断言,需要加jvm参数 -ea
     */
    private static void fun() {
        //断言1结果为true，则继续往下执行
        assert true;
        System.out.println("断言1没有问题，Go！");

        System.out.println("\n-----------------\n");

        //断言2结果为false,程序终止
        assert false : "断言失败，此表达式的信息将会在抛出异常的时候输出！";
        System.out.println("断言2没有问题，Go！");
    }
}
