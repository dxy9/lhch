package com.lhch.book.java8inaction.chap6;

import com.lhch.book.java8inaction.Dish;
import com.lhch.util.JSONUtil;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

/**
 * 分组
 * 为什么无法静态导入????
 *
 * @author linhaicheng on 2019/04/20
 */
public class GroupMain {

    public static void main(String[] args) {
        //fun1(Dish.menu);
        //fun2(Dish.menu);
        //fun3(Dish.menu);
        //fun4(Dish.menu);
        //fun5(Dish.menu);
        fun7(Dish.menu);
    }

    /**
     * groupingby和mapping方法的结合
     * 每种类型中含有的卡路里level
     * lamda表达式的类型让人摸不到头脑
     * lamda 大量使用的话,首先需要搞明白参数的位置
     */
    private static void fun7(List<Dish> dishes) {
        Map<Dish.Type, Set<level>> collect = dishes.stream()
                .collect(groupingBy(a -> a.getType(),
                        mapping(
                                a -> {
                                    if (a.getCalories() <= 400) return level.LOWER;
                                    else if (a.getCalories() <= 700) return level.NORMAL;
                                    else return level.HIGHT;
                                }
                                , toSet()
                        )/*,toSet()不是放到这里的
                         toSet()*/));
        System.out.println(collect);
    }

    /**
     * 每一组的卡路里求和
     * 原本看似非常简单的一个求和,编写起来也是问题很多=>练习的重要性
     * 感觉非常简单,做起来就非常难,寸步难行,这就是现在自己面临的问题
     */
    private static void fun6(List<Dish> dishes) {
        Map<Dish.Type, Double> collect = dishes.stream()
                .collect(groupingBy(a -> a.getType(),
                        summingDouble(Dish::getCalories)));
        /*
        分析:
            必须返回一个Collector
            Collector不是一个函数式接口
            Collector中静态方法的参数都是函数式接口的对象
            还是对方法的返回值和参数必须很清晰
         */
        Map<Dish.Type, Double> collect1 = dishes.stream()
                .collect(groupingBy(Dish::getType,
                        summingDouble(d -> d.getCalories())));
    }

    /**
     * 把收集器转换成另外一个类型
     * 求类型中卡路里最后的菜
     */
    private static void fun5(List<Dish> dishes) {
        Map<Dish.Type, Dish> collect = dishes.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        collectingAndThen(maxBy(Comparator.comparingInt(Dish::getCalories)),
                                Optional::get
                        )));
        System.out.println(collect);

        Map<Dish.Type, Dish> collect1 = dishes.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        collectingAndThen(maxBy(Comparator.comparing(Dish::getCalories)),
                                Optional::get
                        )));

        Map<Dish.Type, Dish> collect2 = dishes.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        collectingAndThen(maxBy((d1, d2) -> Integer.compare(d1.getCalories(), d2.getCalories())),
                                Optional::get
                        )));
        System.out.println("collection2:" + collect2);

    }

    /**
     * 按照子组收集数据
     */
    private static void fun4(List<Dish> dishes) {
        Map<Dish.Type, Long> collect = dishes.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.counting()));
        System.out.println(collect);
    }

    /**
     * 多级分组
     */
    private static void fun3(List<Dish> dishes) {
        Map<Dish.Type, Map<level, List<Dish>>> collect = dishes.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        Collectors.groupingBy(a -> {
                            if (a.getCalories() > 600) {
                                return level.HIGHT;
                            }
                            if (a.getCalories() <= 600 && a.getCalories() > 400) {
                                return level.NORMAL;
                            }
                            return level.LOWER;
                        })));
        System.out.println(collect);

    }

    private static void fun2(List<Dish> dis) {
        Map<level, List<Dish>> collect = dis.stream()
                .collect(Collectors.groupingBy(a -> {
                    if (a.getCalories() > 600) {
                        return level.HIGHT;
                    }
                    if (a.getCalories() <= 600 && a.getCalories() > 400) {
                        return level.NORMAL;
                    }
                    return level.LOWER;
                }));
        System.out.println(collect);
    }

    /**
     * 按照菜肴的类型进行分组
     */
    private static void fun1(List<Dish> dishes) {
        Map<Dish.Type, List<Dish>> collect = dishes.stream()
                //.map()
                .collect(Collectors.groupingBy(Dish::getType));
        //collect.forEach(value-> JSONUtil.toJSON(value));
        System.out.println(JSONUtil.toJSON(collect));
        //直接打印就好
        System.out.println(collect);
    }

    /**
     * 按照卡路里进行分组
     */
    enum level {
        LOWER, NORMAL, HIGHT
    }
}
