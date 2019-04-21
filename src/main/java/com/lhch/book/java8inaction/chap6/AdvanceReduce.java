package com.lhch.book.java8inaction.chap6;

import com.lhch.book.java8inaction.Dish;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author linhaicheng on 2019/04/20
 */
public class AdvanceReduce {
    public static void main(String[] args) {
        //fun(Dish.menu);
        fun4(Dish.menu);
    }

    /**
     * 连接字符串
     */
    private static void fun4(List<Dish> dishes) {
        String string = dishes.stream()
                .map(Dish::getName)
                //.collect(Collectors.joining(","))
                .reduce("", (a, b) -> a + "," + b);
        System.out.println(string);

        String collect = dishes.stream()
                .map(Dish::getName)
                .collect(Collectors.joining/*加入*/(","));
        System.out.println("joining:" + collect);

    }

    /**
     * 使用收集器进行汇总
     */
    private static void fun3(List<Dish> dishes) {
        dishes.stream()
                //.map(Dish::getCalories) 不需要
                .collect(Collectors.summingInt(Dish::getCalories))
        ;
    }

    /**
     * 使用收集器的方式求最大值
     */
    private static void fun2(List<Dish> dishes) {
        dishes.stream()
                .map(Dish::getCalories)
                .collect(Collectors.maxBy(Integer::compareTo))
                .get();
    }

    /**
     * 查询流中的最大值和最小值
     * 先把lambd写出来，再去找对应的方法引用，是正确的思路。=》因为我对引用的方法并不熟悉
     */
    private static void fun(List<Dish> dishes) {
        Integer integer = dishes.stream()
                .map(Dish::getCalories)
                //.max((a, b) -> a.compareTo(b))
                .max(Integer::compareTo)
                .get();
        System.out.println(integer);
    }
}
