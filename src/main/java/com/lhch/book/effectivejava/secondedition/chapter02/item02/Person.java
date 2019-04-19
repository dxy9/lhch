package com.lhch.book.effectivejava.secondedition.chapter02.item02;

import lombok.Data;

/**
 * javaBean 模式：
 * 1，处于不一致的状态，试图使用处于不一致状态的对象，将会导致失败？？？
 * 2，阻止了把类变成不可变的可能🔺🔺🔺🔺
 * 重叠构造函数模式:代码混乱
 *
 * @author linhaicheng on 2019/04/16
 */
@Data
public class Person {

    /** 不可变的属性 */
    /** required paramters */
    private final String name;
    private final String gender;
    private final String height;
    private final String weight;
    private final String hobby;

    /** 定义为private就可以 */
    private Person(Builder builder) {
        this.name = builder.name;
        this.gender = builder.gender;
        this.height = builder.height;
        this.weight = builder.weight;
        this.hobby = builder.hobby;

    }

    public static void main(String[] args) {
        /*直接可以创建内部类的对象*/
        Person person = new Builder("zhangsan", "male")
                .height("1.8M")
                .weight("200斤")
                .hobby("dushu")
                .build();

        System.out.println(person);
    }

    public static class Builder {

        /** required paramters */
        private String name;
        private String gender;
        /** optional paramters */
        private String height;
        private String weight;
        private String hobby;

        /** 必选的属性 */
        public Builder(String name, String gender) {
            this.name = name;
            this.gender = gender;
        }

        /** 可选属性方法 */
        public Builder height(String height) {
            this.height = height;
            return this;
        }

        public Builder weight(String weight) {
            this.weight = weight;
            return this;
        }

        public Builder hobby(String hobby) {
            this.hobby = hobby;
            return this;
        }

        /** 构建方法 */

        public Person build() {
            return new Person(this);
        }

    }

}
