package com.lhch.book.java8inaction.chap4;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

/*静态导入*/

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

/**
 * 菜肴
 */
@Data
@AllArgsConstructor
class Dish {

    public static final List<Dish> menu =
            Arrays.asList(new Dish("pork", false, 800, Dish.Type.MEAT),
                    new Dish("beef", false, 700, Dish.Type.MEAT),
                    new Dish("chicken", false, 400, Dish.Type.MEAT),
                    new Dish("french fries", true, 530, Dish.Type.OTHER),
                    new Dish("rice", true, 350, Dish.Type.OTHER),
                    new Dish("season fruit", true, 120, Dish.Type.OTHER),
                    new Dish("pizza", true, 550, Dish.Type.OTHER),
                    new Dish("prawns", false, 400, Dish.Type.FISH),
                    new Dish("salmon", false, 450, Dish.Type.FISH));
    private final String name;
    /** 是否为素菜 */
    private final boolean vegetarian;
    /** 卡路里 */
    private final int calories;
    private final Type type;

    public static void main(String[] args) {

        // lombok和自定义的toString方法不会冲突
        Dish salmon = new Dish("salmon", false, 450, Type.FISH);
        System.out.println(salmon);
    }

    @Override
    public String toString() {
        return name;
    }

    /** 内置枚举：菜肴的类型 */
    public enum Type {
        MEAT, FISH, OTHER
    }
}