package com.lhch.fun.java7;

import java.util.LinkedList;
import java.util.List;

/**
 * @author linhaicheng on 2019/04/19
 */
public class Java7Main {

    /**
     * java7 新特性 菱形运算符
     */
    private static void fun() {
        // java7允许的菱形运算符代码如下
        List<String> list = new LinkedList<>();
        // but java5/6就可以这么写=>使用了原始类型,并且丢失了所有的类型检查
        //List<String> list=new LinkedList();
        List<String> strings = null;//... // some list that contains some strings

        // Totally legal since you used the raw type and lost all type checking!
        // 完全合法，因为你使用原始类型并丢失所有类型检查！
        List<Integer> integers = new LinkedList(strings);
        // Not legal since the right side is actually generic!
        // 不合法，因为右侧实际上是通用的！
        //List<Integer> integers1 = new LinkedList<>(strings);

    }
}
