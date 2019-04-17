package com.haicheng.book.effectivejava.secondedition.chapter02.item02;

import lombok.Builder;
import lombok.Data;

/**
 * 测试lombok提供的Builder
 *
 * @author linhaicheng on 2019/04/16
 */
@Data
@Builder
public class PersonLombok {

    /** 不可变的属性 */
    /** required paramters */
    private final String name;
    private final String gender;
    private final String height;
    private final String weight;
    private final String hobby;

    public static void main(String[] args) {
        // lombok 提供静态方法
        PersonLombok build = PersonLombok.builder()
                .name("张三")
                .gender("fmale")
                .hobby("football")
                .build();
    }

}
