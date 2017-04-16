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

        Predicate<String> predicate = s -> s.length() > 0;
        predicate.test("abc");
        predicate.negate().test("abc");

        Predicate<Boolean> nonNull = Objects::nonNull;
        Predicate<Boolean> isNull = Objects::isNull;

        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate();
    }

    //    function
    void functionAccess() {

        Function<String, Integer> toInteger = Integer::valueOf;
        Function<String, String> backToString = toInteger.andThen(String::valueOf);

        backToString.apply("123");
    }

    //    supplier
    void supplierAccess() {
        Supplier<Person> personSupplier = Person::new;
        personSupplier.get();

    }

    //    consumer
    void consumerAccess() {
        Consumer<Person> greeter = person -> System.out.println("I miss " + person.firstName + " " + person.lastName);
        greeter.accept(new Person("bao", "shali"));
    }

    //    comparator
    void comparatorAccess() {
        Comparator<Person> comparator = (p1, p2) -> p1.firstName.compareTo(p2.firstName);
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

        optional.ifPresent(a -> System.out.println(a.charAt(0)));
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

//        filter 过滤通过一个predicate接口来过滤并只保留符合条件的元素，该操作属于中间操作，所以我们可以在过滤后的结果来应用其他Stream操作（比如forEach）。forEach需要一个函数来对过滤后的元素依次执行。forEach是一个最终操作，所以我们不能在forEach之后来执行其他Stream操作。
        stringCollection.stream().filter(s -> s.startsWith("b")).forEach(System.out::print);
        System.out.println("\nfilter end");
//        sort 排序是一个中间操作，返回的是排序好后的Stream。如果你不指定一个自定义的Comparator则会使用默认排序。
        stringCollection.stream().sorted().forEach(System.out::print);
        System.out.println("\nsorted end");
//        需要注意的是，排序只创建了一个排列好后的Stream，而不会影响原有的数据源，排序之后原数据stringCollection是不会被修改的：
        System.out.print(stringCollection);
        System.out.println("\nnon sorted end");
//        map 中间操作map会将元素根据指定的Function接口来依次将元素转成另外的对象，下面的示例展示了将字符串转换为大写字符串。你也可以通过map来讲对象转换成其他类型，map返回的Stream类型是根据你map传递进去的函数的返回值决定的。
        stringCollection.stream().map(String::toUpperCase).forEach(System.out::print);
        System.out.println("\nmap end");
//        count 计数是一个最终操作，返回Stream中元素的个数，返回值类型是long。
        long count = stringCollection.stream().filter(s -> s.startsWith("a")).count();
        System.out.print(count);
        System.out.println("\ncount end");
//        reduce 这是一个最终操作，允许通过指定的函数来讲stream中的多个元素规约为一个元素，规越后的结果是通过Optional接口表示的：
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
//        串行 串行Stream上的操作是在一个线程中依次完成
        long t0 = System.nanoTime();
        long count = values.stream().count();
        System.out.println(count);
        long t1 = System.nanoTime();
        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("sequential sort took: %d ms", millis));
//        并行 并行Stream则是在多个线程上同时执行。唯一需要做的改动就是将stream()改为parallelStream()。
        long t3 = System.nanoTime();
        count = values.parallelStream().count();
        System.out.println(count);
        long t4 = System.nanoTime();
        millis = TimeUnit.NANOSECONDS.toMillis(t4 - t3);
        System.out.println(String.format("parallel sort took: %d ms", millis));
    }

    //    map Merge做的事情是如果键名不存在则插入，否则则对原键对应的值做合并操作并重新插入到map中。
    void mapAccess() {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            map.putIfAbsent("val" + i, i);
        }

        map.merge("val10", 100, (val, oval) -> ++val);
        System.out.println(map.get("val10"));
        map.merge("val10", 100, (val, oval) -> ++val);
        System.out.println(map.get("val10"));
    }

    public static void main(String[] args) {
        InterfaceAccess interfaceAccess = new InterfaceAccess();
//        interfaceAccess.consumerAccess();
//
//        interfaceAccess.optionalAccess();
//        interfaceAccess.streamAccess();
//        interfaceAccess.mutiStreamAccess();
        interfaceAccess.mapAccess();
    }
}
