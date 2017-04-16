# learnjdk8
1.给接口增加非抽象方法
```
public interface Formula {
    double calculate(int a);
    default double sqrt(int a) {
        return Math.sqrt(a);
    }
}

public class FormulaImpl implements Formula {
    @Override
    public double calculate(int a) {
        return sqrt(a * 100);
    }

    public static void main(String[] args) {
        FormulaImpl formula = new FormulaImpl();
        double calculate = formula.calculate(1);

        double sqrt = formula.sqrt(16);
        System.out.println(calculate + " " + sqrt);
    }


}
```
2. lambda 表达式
```
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
```
3. 函数式接口访问
```
@FunctionalInterface
public interface Converter<F,T> {
    T converter(F from);
}

Converter<String, Integer> converter = (from -> Integer.valueOf(from));
        Integer integer = converter.converter("123");
        System.out.println(integer);
```
4. 方法与构造函数引用

```
public class Person {
    String firstName;
    String lastName;

    Person() {

    }

    Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
public interface PersonFactory<P extends  Person> {

    P create(String firstName, String lastName);
}

        PersonFactory<Person> personFactory = Person::new;

        Person person = personFactory.create("liu", "zhongxu");

        System.out.println(person.firstName + person.lastName);
//我们只需要使用 Person::new 来获取Person类构造函数的引用，Java编译器会自动根据PersonFactory.create方法的签名来选择合适的构造函数。
```
5. lambda作用域
```
public class LocalVariable {
    public static void main(String[] args) {
        int num = 1;//num必须不可被后面的代码修改（即隐性的具有final的语义）
        Converter<Integer, String> converter = (from -> String.valueOf(from + num));
        System.out.println(converter.converter(1));

        LocalVariable localVariable = new LocalVariable();
        localVariable.scopes();

    }

    public static int outerStaticNum;
    int outerNum;

    void scopes() {//lambda内部对于实例的字段以及静态变量是即可读又可写。
        Converter<Integer,String> stringConverter = from -> {outerNum=32;return String.valueOf(from);};
        Converter<Integer,String> stringConverter1 = from -> {outerStaticNum=99;return String.valueOf(from);};
        System.out.println(stringConverter.converter(1)+outerNum);
        System.out.println(stringConverter1.converter(1)+outerStaticNum);
    }
}
```
6. 访问接口的默认方法
```
see @InterfaceAccess
//Predicate 接口只有一个参数，返回boolean类型。该接口包含多种默认方法来将Predicate组合成其他复杂的逻辑（比如：与，或，非）
//Function 接口有一个参数并且返回一个结果，并附带了一些可以和其他函数组合的默认方法（compose, andThen）
//Supplier 接口返回一个任意范型的值，和Function接口不同的是该接口没有任何参数
//Consumer 接口表示执行在单个参数上的操作
//Comparator 接口
//Optional 接口 Optional 不是函数是接口，这是个用来防止NullPointerException异常的辅助类型
//Stream 接口 java.util.Stream 表示能应用在一组元素上一次执行的操作序列。Stream 操作分为中间操作或者最终操作两种，最终操作返回一特定类型的计算结果，而中间操作返回Stream本身，这样你就可以将多个操作依次串起来。Stream 的创建需要指定一个数据源，比如 java.util.Collection的子类，List或者Set， Map不支持。Stream的操作可以串行执行或者并行执行。
```
7. Date Api
8. Annotation
9. CompletableFuture
