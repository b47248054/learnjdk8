package com.liuzx.learnjdk8;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * Created by wanye on 2017/4/16.
 */
public class InterfaceAccess {

    //    predicate
    void predicateAccess() {

        Predicate<String> predicate=s -> s.length()>0;
        predicate.test("abc");
        predicate.negate().test("abc");

        Predicate<Boolean> nonNull = Objects::nonNull;
        Predicate<Boolean> isNull=Objects::isNull;

        Predicate<String> isEmpty=String::isEmpty;
        Predicate<String> isNotEmpty=isEmpty.negate();
    }
}
