package com.vlasenko;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CalculatorJavaMentor {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter the expression (Ex: 2+2): ");
        boolean isFinish = false;

        while (!isFinish) {
            String expression = reader.readLine();
            if (expression.toLowerCase().contains("end")) {
                isFinish = true;
                break;
            }
/*
        List<String > strList = Arrays.asList(expression.split("[\\+\\-\\*\\/]"));
        List<Integer> intList = strList.stream()
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        intList.forEach(operand -> System.out.println(operand));

 */
            String[] strOperands = expression.split("[\\+\\-\\*\\/]");
            int[] intOperands = Arrays.stream(strOperands).mapToInt(Integer::parseInt).toArray();

            for (int i = 0; i < intOperands.length; i++) {
                if (intOperands[i] < 0 || intOperands[i] > 9) {
                    System.out.println("Numbers should be between 0 and 10");
                    break;
                }
            }
            String[] operators = expression.substring(1).split("[0-9]+");

            // Arrays.stream(intOperands).forEach(num -> System.out.println(num));

            CalculatorEngine calculatorJavaMentor = new CalculatorEngine(intOperands[0], intOperands[1], operators[0]);
        }
    }
}
