package com.liuzx.learnjdk8;

/**
 * Created by wanye on 2017/4/16.
 */
@FunctionalInterface
public interface Converter<F,T> {
    T converter(F from);
}
