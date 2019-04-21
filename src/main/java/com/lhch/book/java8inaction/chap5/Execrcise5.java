package com.lhch.book.java8inaction.chap5;

import com.lhch.util.JSONUtil;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 习题
 *
 * @author linhaicheng on 2019/04/19
 */
public class Execrcise5 {

    public static final int[] intArr = new int[]{1, 4, 5, 6, 2, 10};
    public static final List<Integer> intList = Arrays.asList(1, 2, 4, 5, 2);
    public static final List<Integer> aList = Arrays.asList(2, 4);
    public static final List<Integer> bList = Arrays.asList(2, 4, 5, 2);

    public static void main(String[] args) {
        //fun1(intArr);
        //fun2(intList);
        //fun3(aList,bList);
        //fun3_1(aList,bList);
        fun4(aList, bList);

    }

    /**
     * 接上面的例子,返回总和能被3整除的数对
     * [1,3] [2,3] to [(1,2),(1,3),(3,2),(3,3)]
     */
    private static void fun4(List<Integer> aList, List<Integer> bList) {
        ArrayList<Map> resultList = new ArrayList<Map>();
        List<int[]> collect = aList.stream()
                .flatMap(a -> bList.stream().map(b -> new int[]{a, b}))
                .filter(a -> (a[1] + a[0]) % 3 == 0)
                .collect(Collectors.toList());
        collect.forEach(Arrays::toString);
        System.out.println(JSONUtil.toJSON(collect));
    }

    /**
     * 返回元素的数对:书本上的实现
     * [1,3] [2,3] to [(1,2),(1,3),(3,2),(3,3)]
     */
    private static void fun3_1(List<Integer> aList, List<Integer> bList) {
        ArrayList<Map> resultList = new ArrayList<Map>();
        List<int[]> collect = aList.stream()
                .flatMap(a -> bList.stream().map(b -> new int[]{a, b})).collect(Collectors.toList());
        collect.forEach(Arrays::toString);
        System.out.println(JSONUtil.toJSON(collect));
    }

    /**
     * 返回元素的数对
     * [1,3] [2,3] to [(1,2),(1,3),(3,2),(3,3)]
     */
    private static void fun3(List<Integer> aList, List<Integer> bList) {
        ArrayList<Map> resultList = new ArrayList<Map>();
        List<HashMap<Integer, Integer>> collect = aList.stream()
                .flatMap(a -> bList.stream().map(b -> {
                    HashMap<Integer, Integer> map = new HashMap<>();
                    map.put(a, b);
                    return map;
                })).collect(Collectors.toList());
        collect.forEach(System.out::println);
        System.out.println(collect.toString());
    }

    /**
     * 求给定列表中元素的平方的列表
     *
     * @param list
     */
    private static List<Integer> fun2(List<Integer> list) {
        List<Integer> collect = list.stream()
                .map(x -> x * x)
                .collect(Collectors.toList());
        collect.forEach(System.out::println);
        return collect;
    }

    /**
     * 求给定的数字数组(列表)的平方的数字数组
     * 数组的选型有问题=>不要再轻易选择基本类型了
     */
    private static int[] fun(int[] ints) {
        //无法简单实现,从int数组到intStream
        //ArrayList<Integer> ints1 = new ArrayList<Integer>(Arrays.asList(ints));
        IntStream intStream = Arrays.stream(ints)
                .map(x -> x * x);

        ArrayList<Integer> list = new ArrayList<Integer>();
        return null;
    }

    /**
     * 数组转换成集合
     */
    private static void fun1(int[] ints) {
        // 错误的转换方法=>因为int是基本数据类型
        Integer[] integerArr = new Integer[]{1, 2, 3};
        List<int[]> ints1 = Arrays.asList(ints);
        // 这样的问题很多,总是让我感觉到莫名其妙,是因为我没有发现里面的细节差别
        // 再就是我为什么非得用一个数组,而不是用集合list=>这就是差距,从选择就开始了
        // 差距从选择就开始了.=>因为我不知道该如何选择和设计,才导致了后面的很多的不必要的问题
        List<Integer> integers1 = Arrays.asList(integerArr);
        System.out.println(Arrays.toString(ints1.get(0)));

        //不能调用添加方法,不支持的操作
        List<Integer> integers = Arrays.asList(1, 2, 4);
        //integers.add(5);
        System.out.println(integers.toString());

        //使用循环=》最笨的方法
        String[] array = {"a", "b", "c"};
        List<String> resultList = new ArrayList<>(array.length);
        for (String s : array) {
            resultList.add(s);
        }

        //内部类ArrayList类的二次封装
        List<String> resultList1 = new ArrayList<>(Arrays.asList(array));
        resultList.add("DD");
        System.out.println("ArrayList二次封装:" + resultList.toString());

        //使用Collections.addAll
        List<String> targetList = new ArrayList<>(array.length);
        Collections.addAll(targetList, array);

        //java9之后提供的方法,跟Arrays.toList是一样的效果
        //List<String> list=List.of(array);

    }
}
