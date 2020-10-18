package com.vlasenko;

public class CalculatorEngine {
    public CalculatorEngine(int one, int two, String oper) {
        calculate(one, two, oper);
    }

    public void calculate(int one, int two, String oper) {

        if (oper.equals("+")) {
            System.out.println(one + two);
        } else if (oper.equals("-")) {
            System.out.println(one - two);
        } else if (oper.equals("*")) {
            System.out.println(one * two);
        } else if (oper.equals("/")) {
            try {
                System.out.println(one/two);
            } catch (ArithmeticException e) {
                System.out.println("You cannot divide by zero");
                throw new ArithmeticException();
            }
        }
    }
}
