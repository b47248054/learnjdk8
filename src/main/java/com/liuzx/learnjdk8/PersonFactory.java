package com.liuzx.learnjdk8;

/**
 * Created by wanye on 2017/4/16.
 */
public interface PersonFactory<P extends  Person> {

    P create(String firstName, String lastName);
}
