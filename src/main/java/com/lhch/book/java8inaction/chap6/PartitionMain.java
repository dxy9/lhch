package com.lhch.book.java8inaction.chap6;

import com.lhch.book.java8inaction.Dish;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

/**
 * 终于到分区了
 *
 * @author linhaicheng on 2019/04/20
 */
@Slf4j
public class PartitionMain {

    public static void main(String[] args) {
        //fun1(Dish.menu);
        fun2(100);
        //fun3();
    }

    /**
     * 测试range和rangeClosed
     */
    private static void fun3() {
        IntStream.range(0, 10)
                .forEach(System.out::println);
        IntStream.rangeClosed(0, 10)
                .forEach(System.out::println);

    }

    /**
     * 将数字按照质数和非质数进行分区
     * 质数（prime number）又称素数，有无限个。质数定义为在大于1的自然数中，除了1和它本身以外不再有其他因数。
     */
    private static void fun2(int n) {
        Map<Boolean, List<Integer>> collect = IntStream.rangeClosed(0, n)
                .boxed()
                .collect(partitioningBy(a -> {
                    boolean prime = isPrime(a);
                    log.info("number:{},isPrime:{}", a, prime);
                    return prime;
                }));
        log.info("collect:{}", collect);
    }

    private static boolean isPrime(int candidate/*候选人*/) {

        //取平方根? TODO
        //必须从2开始,将1排除 !!!!! 又浪费了时间=>逻辑不是靠试,要自己想到
        return IntStream.range/*Closed*/(2, candidate)
                .noneMatch(i -> {
                    boolean b = candidate % i == 0;
                    log.info("candicate:{},i:{}", candidate, i);
                    return b;
                });

    }

    /**
     * 按照素食和非素食进行分区
     */
    private static void fun1(List<Dish> dishes) {

        //多级分区
        //Map<Boolean, Map<Boolean, List<Dish>>> collect3 = dishes.stream()
        Map<Boolean, Long> collect3 = dishes.stream()
                .collect(partitioningBy(Dish::isVegetarian,
                        //partitioningBy(a -> a.getCalories() > 500)));
                        counting()));
        log.info("collection2:{}", collect3);

        /*素食和非素食中热量最高的菜*/
        /*
        没有类型变量的实例存在以使Dish符合List <Dish>推理变量D具有不兼容的边界：等式约束：List <Dish>下限：Dish
        返回值的问题
         */
        Map<Boolean, Dish> collect2 = dishes.stream()
                .collect(partitioningBy(Dish::isVegetarian,
                        collectingAndThen(
                                maxBy(comparing(Dish::getCalories)),
                                Optional::get)));
        log.info("collection2:{}", collect2);

        Map<Boolean, List<Dish>> collect = dishes.stream()
                .collect(partitioningBy(Dish::isVegetarian));
        log.info("collection:{}", collect);

        /*二级*/
        Map<Boolean, Map<Dish.Type, List<Dish>>> collect1 = dishes.stream()
                .collect(partitioningBy(Dish::isVegetarian,
                        groupingBy(Dish::getType)));
        log.info("collection1:{}", collect1);

    }
}
