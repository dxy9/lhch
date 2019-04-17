package com.haicheng.fun.annotation;

/**
 * @author linhaicheng on 2019/04/13
 */
public class PersonImpl extends AbstractPersonImpl {

    //没有@Overwrite!!!!!!!!!!!!!!!!!!!

    //覆盖
    @Override
    public void play() {
        super.play();
    }

    @Override
    protected void hate() {
        System.out.println("i hate 浪费时间");
    }
}
