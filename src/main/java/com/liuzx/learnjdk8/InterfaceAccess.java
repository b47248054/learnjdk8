package com.liuzx.learnjdk8;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

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

    //    function
    void functionAccess() {

        Function<String,Integer> toInteger = Integer::valueOf;
        Function<String,String> backToString = toInteger.andThen(String::valueOf);

        backToString.apply("123");
    }

    //    supplier
    void supplierAccess() {
        Supplier<Person> personSupplier = Person::new;
        personSupplier.get();

    }

    //    consumer
    void consumerAccess() {
        Consumer<Person> greeter = person -> System.out.println("I miss "+person.firstName+" "+person.lastName);
        greeter.accept(new Person("bao","shali"));
    }

    //    comparator
    void comparatorAccess() {
        Comparator<Person>  comparator = (p1,p2) -> p1.firstName.compareTo(p2.firstName);
        Person p1 = new Person("liu", "zhongxu");
        Person p2 = new Person("bao", "shali");
        comparator.compare(p1, p2);
        comparator.reversed().compare(p1, p2);
    }

    //    optional
    void optionalAccess() {
        Optional<String> optional = Optional.of("liu");
        optional.isPresent();
        optional.get();
        optional.orElse("bao");

        optional.ifPresent(a-> System.out.println(a.charAt(0)));
    }

    //    stream
    void streamAccess() {
        List<String> stringCollection = new ArrayList<String>();
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");

//        filter
        stringCollection.stream().filter(s -> s.startsWith("b")).forEach(System.out::print);
        System.out.println("\nfilter end");
//        sort
        stringCollection.stream().sorted().forEach(System.out::print);
        System.out.println("\nsorted end");
        System.out.print(stringCollection);
        System.out.println("\nnon sorted end");
//        map
        stringCollection.stream().map(String::toUpperCase).forEach(System.out::print);
        System.out.println("\nmap end");
//        count
        long count = stringCollection.stream().filter(s -> s.startsWith("a")).count();
        System.out.print(count);
        System.out.println("\ncount end");
//        reduce
        Optional<String> reduce = stringCollection.stream().sorted().reduce((s1, s2) -> s1 + "#" + s2);
        reduce.ifPresent(System.out::print);
        System.out.println("\nreduce end");

    }

    //    mutistream
    void mutiStreamAccess() {
        int max = 1000000;
        List<String> values = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }
//        串行
        long t0 = System.nanoTime();
        long count = values.stream().count();
        System.out.println(count);
        long t1 = System.nanoTime();
        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("sequential sort took: %d ms", millis));
//        并行
        long t3 = System.nanoTime();
        count = values.parallelStream().count();
        System.out.println(count);
        long t4 = System.nanoTime();
        millis = TimeUnit.NANOSECONDS.toMillis(t4 - t3);
        System.out.println(String.format("parallel sort took: %d ms", millis));
    }

    //    map
    void mapAccess() {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            map.putIfAbsent("val" + i,i);
        }

        map.merge("val10", 100, (val,oval) -> ++val);
        System.out.println(map.get("val10"));
        map.merge("val10", 100, (val,oval) -> ++val);
        System.out.println(map.get("val10"));
    }
    public static void main(String[] args) {
        InterfaceAccess interfaceAccess = new InterfaceAccess();
//        interfaceAccess.consumerAccess();
//
//        interfaceAccess.optionalAccess();
//        interfaceAccess.streamAccess();
//
//        interfaceAccess.mutiStreamAccess();
        interfaceAccess.mapAccess();
    }
}
