package com.lhch.book.java8inaction.chap5;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * 映射
 * 接收一个Function参数:前面的泛型是参数,后面的泛型是返回值
 */
public class Mapping {

    public static void main(String... args) {

        // map
        List<String> words = Arrays.asList("Hello", "World");
        List<Integer> wordLengths = words.stream()
                //实例方法引用int length=s.length();
                .map(String::length)
                .collect(toList());
        System.out.println(wordLengths);
        // map
        words.stream()
                .map(s -> s.length())
                .collect(Collectors.toList())
                .forEach(System.out::println);
        // map
        words.stream()
                //.map(s ->{return s+":"+s.length()})
                .map(s -> s + ":" + s.length())
                .collect(Collectors.toList())
                .forEach(System.out::println);

        // flatMap
        words.stream()
                //.flatMap((String line) -> Arrays.stream(line.split("")))
                /*
                泛型的限定,R为返回值,T为参数.在这边T输入参数为String,R输出参数也是String
                <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper);
                flatMap<Function<String,Stream<String>>>
                if use map(), return Stream<Stream<String>>
                 */
                //.flatMap(line -> Arrays.stream(line.split("")))
                //这种能否使用静态方法引用的方法?? TODO
                .map(line -> line.split(""))
                //静态方法引用,输入参数和输出参数类型相同?? TODO
                .flatMap(Arrays::stream)
                .distinct()
                .forEach(System.out::println);

        // flatMap
        List<Integer> numbers1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> numbers2 = Arrays.asList(6, 7, 8);
        List<int[]> pairs =
                numbers1.stream()
                        // Stream<Integer>=>Stream<int[]> 这边涉及到参数的组合=>外层lamd和内层lamd参数组合
                        .flatMap((Integer i) -> numbers2.stream().map((Integer j) -> new int[]{i, j}))
                        .filter(pair -> (pair[0] + pair[1]) % 3 == 0)
                        .collect(toList());
        pairs.forEach(pair -> System.out.println("(" + pair[0] + ", " + pair[1] + ")"));
    }
}
