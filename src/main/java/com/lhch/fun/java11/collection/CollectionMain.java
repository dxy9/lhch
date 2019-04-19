package com.lhch.fun.java11.collection;

/**
 * @author linhaicheng on 2019/04/19
 */
public class CollectionMain {

    /**
     * 新增的集合创建方法
     */
    private static void fun() {
        int[] intArr = new int[]{1, 2, 3};

        //从java8切换到java11,需要更改4个位置的java版本
        //这结果不是自己想要的=>需要用引用类型而不是基本类型
        //List<int[]> intArr1 = List.of(intArr);
    }
}
