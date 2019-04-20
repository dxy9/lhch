package com.lhch.book.java8inaction.chap4;

import com.lhch.book.java8inaction.Dish;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class StreamBasic {

    public static void main(String... args) {
        // Java 7
        getLowCaloricDishesNamesInJava7(Dish.menu).forEach(System.out::println);

        System.out.println("---");

        // Java 8
        getLowCaloricDishesNamesInJava8(Dish.menu).forEach(System.out::println);

    }

    /**
     * 用java7 的方法获取卡路里低的菜肴
     *
     * @param dishes
     * @return
     */
    public static List<String> getLowCaloricDishesNamesInJava7(List<Dish> dishes) {
        List<Dish> lowCaloricDishes = new ArrayList<>();
        //选取卡路里低于400的菜肴
        for (Dish d : dishes) {
            if (d.getCalories() < 400) {
                lowCaloricDishes.add(d);
            }
        }
        //按照卡路里对菜肴进行排序
        List<String> lowCaloricDishesName = new ArrayList<>();
        Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
            public int compare(Dish d1, Dish d2) {
                return Integer.compare(d1.getCalories(), d2.getCalories());
            }
        });
        //返回菜肴列表
        for (Dish d : lowCaloricDishes) {
            lowCaloricDishesName.add(d.getName());
        }
        return lowCaloricDishesName;
    }

    public static List<String> getLowCaloricDishesNamesInJava8(List<Dish> dishes) {
        //Stream<Dish>
        return dishes.stream()
                //Stream<Dist>
                .filter(d -> d.getCalories() < 400)
                //sorted Stream<Dish>
                .sorted(comparing(Dish::getCalories))
                //Stream<String>
                .map(Dish::getName)
                //List<String>
                .collect(toList());
    }
}
