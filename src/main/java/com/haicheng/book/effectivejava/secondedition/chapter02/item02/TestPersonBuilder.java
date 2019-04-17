package com.haicheng.book.effectivejava.secondedition.chapter02.item02;

/**
 * @author linhaicheng on 2019/04/16
 */
public class TestPersonBuilder {
    public static void main(String[] args) {
        /** 必须通过外部类才能创建内部类的对象 */
        new Person.Builder("zhangsan", "male").build();
    }
}
