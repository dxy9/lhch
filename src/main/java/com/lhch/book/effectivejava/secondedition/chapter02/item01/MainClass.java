package com.lhch.book.effectivejava.secondedition.chapter02.item01;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 考虑用静态方法代替构造器
 *
 * @author linhaicheng on 2019/04/16
 */
@Slf4j
public class MainClass {

    public static void main(String[] args) {
        Person instance = Person.getInstance();
        instance.setName("zhangsan");
        log.info("person {}", instance.toString());
    }
}

/**
 * 类如果不包含公有的或者受保护哦的构造器，就不能 ==子类化== ，不是实例化
 */
@Data
class Person {
    private static final Person instance = new Person();
    private String name;

    private Person() {
    }

    public static Person getInstance() {
        return instance;
    }
}
