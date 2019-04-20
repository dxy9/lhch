package com.lhch.book.java8inaction;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

/**
 * @author linhaicheng on 2019/04/19
 */
@Data
@AllArgsConstructor
public class Dish {
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
    /**
     * final 不能添加set方法
     */
    //@Setter
    private final String name;
    /** 是否为素菜 */
    private final boolean vegetarian;
    /** 卡路里 */
    private final int calories;
    //@Setter
    private final Type type;

    /**
     * 无参构造同样需要对final的变量记性初始化
     *
     * @param args
     */
    //public Dish() {}
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
