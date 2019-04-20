package com.lhch.book.java8inaction.chap5;

import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 勾股数 毕氏三元数
 *
 * @author linhaicheng on 2019/04/20
 */
public class PythagoreanTriple {

    public static void main(String[] args) {
        //fun1();
        fun2();
    }

    /**
     * 匿名内部类可以定义字段而定义内部状态
     */
    private static void fun3() {
        Stream.generate(new Supplier<Integer>() {
            private Integer a = 1;

            @Override
            public Integer get() {
                return 1;
            }
        });
    }

    /**
     * 斐波那契数列
     * 生成斐波那契元祖中的前二十个
     */
    private static void fun2() {
        //Stream.iterate(1,a->a+1).limit(10).forEach(System.out::println);
        Stream.iterate(new int[]{0, 1}, a -> new int[]{a[1], a[0] + a[1]})
                .limit(20)
                .forEach(a -> System.out.println("(" + a[0] + "," + a[1] + ")"));
    }

    /*
    1 定义三元数
        正确的做法是定义一个类
        这边用数组表示 new int[]{3,4,5} 表示勾股数（3,4,5）
    2 筛选成立的组合
        假设已经知道三元数组的前两个数 a b ，如何判断是否是三元数组呢？
        filter(b->Math.qrtz(a*a+b*b)%1==0) 如果对1取余等于零，则证明是整数
    3 生成三元数组
        假设ab满足三元数组的条件，开始生成三元数组
        filter((a,b)->Math.qrtz(a*a+b*b)%1==0)
        .map((a,b)->new int[]{a*a,b*b,(int)Math.qrtz(a*a+b*b)}
    4 生成b的值和a的值


     */
    private static void fun1() {
        Stream<int[]> stream = IntStream.rangeClosed(1, 100)
                .boxed()
                .flatMap(a ->
                        IntStream.rangeClosed(a, 100)
                                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                                .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                );
        stream.forEach(a -> System.out.println(a[0] + " " + a[1] + " " + a[2]));

        Stream<Stream<int[]>> streamStream = IntStream.rangeClosed(1, 100)
                .boxed()
                .map(a ->
                        IntStream.rangeClosed(a, 100)
                                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                                .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                );

    }

}
