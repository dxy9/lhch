package com.lhch.book.java8inaction.chap5;

import com.lhch.book.java8inaction.Dish;

import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 数值流 IntStream LongStream DoubleStream
 *
 * @author linhaicheng on 2019/04/19
 */

public class NumericalValueStreamMain {
    public static void main(String[] args) {
        //fun();
        //fun1();
        //fun3(Dish.menu);
        fun4(Dish.menu);
    }

    /**
     * 如何生成勾股数?
     * 1,用什么样的获取流的方法
     * 2,
     */
    private static void fun5() {

    }

    /**
     * 数值范围
     */
    private static void fun4(List<Dish> dishes) {
        System.out.println(dishes.stream()
                .map(Dish::getName)
                .reduce((a, b) -> a + "," + b)
                .get());

        /*
         * 数字如何进行lamda排序? TODO
         * 数字字符串如何进行lamda排序? TODO
         * rangle()和rangeClosed()方法都包含最后的数值? TODO
         *
         * */
        String integerJoin =
                //IntStream.range(0, 100)
                IntStream.rangeClosed(0, 100)
                        .filter(i -> i % 10 == 0)
                        .boxed()
                        .map(String::valueOf)
                        .sorted((x, y) -> x.compareTo(y))
                        .reduce((x, y) -> x + " " + y)
                        .get();
        System.out.println("integerJoin:" + integerJoin);
        //.reduce((i,j)->i+" "+j);String cant convet to Integer
    }

    /**
     * 求最大和求和的区别
     * 求和可以有一个默认的和0;求最大没有默认值
     */
    private static void fun3(List<Dish> list) {
        //可选的int
        OptionalInt max = list.stream()
                .mapToInt(Dish::getCalories)
                .max();
        //.orElse(1);提供一个默认值
        int asInt = max.getAsInt();
        System.out.println(asInt);
    }

    /**
     * 数值流和普通流的转换
     */
    private static void fun2() {
        IntStream intStream = Dish.menu.stream()
                .mapToInt(Dish::getCalories);
        //boxed 盒装
        Stream<Integer> boxed = intStream.boxed();
    }

    /**
     * 使用数值流的方式计算元素的总和
     */
    private static void fun1() {
        int sum = Dish.menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();
    }

    /**
     * 使用reduce计算元素的总和
     */
    private static void fun() {

        int i = Dish.menu.stream()
                .map(Dish::getCalories)
                .reduce(0, (x, y) -> x + y);
        //.intValue();
        System.out.println(i);

    }
}
