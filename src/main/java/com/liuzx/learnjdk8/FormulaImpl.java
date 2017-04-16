package com.liuzx.learnjdk8;

/**
 * Created by wanye on 2017/4/16.
 */
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
