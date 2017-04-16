package com.liuzx.learnjdk8;

/**
 * Created by wanye on 2017/4/16.
 */
public class LocalVariable {
    public static void main(String[] args) {
        int num = 1;
        Converter<Integer, String> converter = (from -> String.valueOf(from + num));
        System.out.println(converter.converter(1));

        LocalVariable localVariable = new LocalVariable();
        localVariable.scopes();

    }

    public static int outerStaticNum;
    int outerNum;

    void scopes() {
        Converter<Integer,String> stringConverter = from -> {outerNum=32;return String.valueOf(from);};
        Converter<Integer,String> stringConverter1 = from -> {outerStaticNum=99;return String.valueOf(from);};
        System.out.println(stringConverter.converter(1)+outerNum);
        System.out.println(stringConverter1.converter(1)+outerStaticNum);
    }
}
