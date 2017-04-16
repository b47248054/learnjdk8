package com.liuzx.learnjdk8;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by wanye on 2017/4/16.
 */
public class Sort {
    static List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");

    public static void sortFormual() {
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return b.compareTo(a);
            }
        });
    }

    public static void sortLambda() {
        Collections.sort(names, (a, b) -> b.compareTo(a));

//        Collections.sort(names, Comparator.reverseOrder());
    }

    public static void main(String[] args) {

        System.out.println(names);
        sortLambda();
        System.out.println(names);
        sortFormual();
        System.out.println(names);

        Converter<String, Integer> converter = (from -> Integer.valueOf(from));
        Integer integer = converter.converter("123");
        System.out.println(integer);

        PersonFactory<Person> personFactory = Person::new;

        Person person = personFactory.create("liu", "zhongxu");

        System.out.println(person.firstName + person.lastName);

    }


}
