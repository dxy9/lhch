package com.haicheng.fun.loop;

/**
 * 跳出多层循环
 * @author linhaicheng on 2019/04/13
 */
public class LoopFun {
    public static void main(String[] args) {
        fun1();
        fun2();
    }

    /**
     * @return void
     * @Description:设置flag
     * @Date: 11:10 2019/4/13
     */
    private static void fun2() {
        int array[][] = {{5, 7, 6, 4, 9}, {1, 2, 8, 3, 2}};
        boolean flag = false;
        //循环条件添加flag条件判断
        for (int i = 0; i < array.length && !flag; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] == 8) {
                    flag = true;
                    break;
                }
            }
        }
        System.out.println(flag);
    }

    /**
     * @return void
     * @Description: 设置标志位退出多层循环
     * @Date: 11:08 2019/4/13
     */
    private static void fun1() {
        //循环开头（必须）设置标记位
        OK:
        for (int i = 1; i < 100; i++) {
            for (int j = 1; j <= i; j++) {
                if (i == 10) {
                    break OK;
                }
                System.out.print(i + "*" + j + "=" + i * j);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
