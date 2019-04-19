package com.lhch.fun.designpattern.singleton;

import lombok.Getter;
import lombok.ToString;

/**
 * 枚举
 */
@Getter
@ToString
enum Person {
    ZHANGSAN("zhangsan", "male");
    private String name;
    private String gender;

    Person(String name, String gender) {
        this.name = name;
        this.gender = gender;
    }

    public void show() {
        System.out.println(this);
    }
}

/**
 * @author linhaicheng on 2019/04/14
 */
public class SingletonMainClass {
    public static void main(String[] args) {
        Person.ZHANGSAN.show();
    }
}

/**
 * 饱汉模式
 */
class C1 {
    //是否应该加final?
    private static final C1 c = new C1();

    private C1() {
    }

    public static C1 getInstance() {
        return c;
    }
}

/**
 * 饿汉模式
 */
class C2 {
    //这边不能定义为final =>引用不可变,无法赋值为后面创建的对象的引用
    public static C2 c = null;

    private C2() {
    }

    public static synchronized C2 getInstance() {
        if (c == null) {
            c = new C2();
        }
        return c;
    }
}

/**
 * 静态内部类
 */
class C3 {

}

/**
 * 双重检查
 */
class C5 {

}