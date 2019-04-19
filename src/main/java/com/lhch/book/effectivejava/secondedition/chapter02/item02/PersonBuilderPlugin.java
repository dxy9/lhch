package com.lhch.book.effectivejava.secondedition.chapter02.item02;

import lombok.Data;

/**
 * 测试lombok提供的Builder
 *
 * @author linhaicheng on 2019/04/16
 */
@Data
public class PersonBuilderPlugin {

    /** 不可变的属性 */
    /** required paramters */
    private final String name;
    private final String gender;
    private final String height;
    private final String weight;
    private final String hobby;

    private PersonBuilderPlugin(Builder builder) {
        name = builder.name;
        gender = builder.gender;
        height = builder.height;
        weight = builder.weight;
        hobby = builder.hobby;
    }

    public static void main(String[] args) {
        PersonBuilderPlugin build = new Builder()
                .name("张三")
                .gender("fmale")
                .hobby("football")
                .build();
    }

    public static final class Builder {
        private String name;
        private String gender;
        private String height;
        private String weight;
        private String hobby;

        public Builder() {
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder gender(String gender) {
            this.gender = gender;
            return this;
        }

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

        public PersonBuilderPlugin build() {
            return new PersonBuilderPlugin(this);
        }
    }
}
