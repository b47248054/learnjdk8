package com.liuzx.learnjdk8;

/**
 * Created by wanye on 2017/4/16.
 */
public interface Formula {
    double calculate(int a);
    default double sqrt(int a) {
        return Math.sqrt(a);
    }
}
