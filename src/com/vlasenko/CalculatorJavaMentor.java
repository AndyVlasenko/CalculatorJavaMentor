package com.vlasenko;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CalculatorJavaMentor {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int one, two;
        int numRes;

        System.out.println("Please enter the expression (Ex: 2+2): ");

        while (true) {
            String expression = reader.readLine();
            if (expression.toLowerCase().contains("end")) {
                break;
            }

            String[] strOperands = expression.split("[+\\-*/]");
            String operators = expression.substring(strOperands[0].length(), strOperands[0].length() + 1);

            if (isNumeric(strOperands[0]) && isNumeric(strOperands[1])) {
                one = Integer.parseInt(strOperands[0]);
                two = Integer.parseInt(strOperands[1]);

                if (!isInRange(one, two)) {
                    throw new IllegalArgumentException("Numbers should be between 0 and 10");
                }

                numRes = calculate(one, two, operators);
                System.out.println(numRes);

            } else {
                one = romanToArabic(strOperands[0]);
                two = romanToArabic(strOperands[1]);
                numRes = calculate(one, two, operators);
                System.out.println(arabicToRoman(numRes));
            }
        }
    }

    private static boolean isNumeric(final String str) {
        if (str == null || str.length() == 0) {
            return false;
        }

        return str.chars().allMatch(Character::isDigit);
    }

    private static boolean isInRange(int one, int two) {

        return !(one < 0 || one > 9 || two < 0 || two > 9);
    }

    private static int romanToArabic(String input) {
        String romanNumeral = input.toUpperCase();
        int result = 0;

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;

        while ((romanNumeral.length() > 0) && (i < romanNumerals.size())) {
            RomanNumeral symbol = romanNumerals.get(i);
            if (romanNumeral.startsWith(symbol.name())) {
                result += symbol.getValue();
                romanNumeral = romanNumeral.substring(symbol.name().length());
            } else {
                i++;
            }
        }

        if (romanNumeral.length() > 0) {
            throw new IllegalArgumentException(input + " cannot be converted to a Roman Numeral");
        }

        return result;
    }

    private static String arabicToRoman(int number) {
        if ((number <= 0) || (number > 81)) {
            throw new IllegalArgumentException(number + " is not in range (0,81)");
        }

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((number > 0) && (i < romanNumerals.size())) {
            RomanNumeral currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= number) {
                sb.append(currentSymbol.name());
                number -= currentSymbol.getValue();
            } else {
                i++;
            }
        }

        return sb.toString();
    }

    private static int calculate(int one, int two, String oper) {
        int res = 0;

        switch (oper) {
            case "+":
                res = (one + two);
                break;
            case "-":
                res = (one - two);
                break;
            case "*":
                res = (one * two);
                break;
            case "/":
                try {
                    res = (one / two);
                    break;
                } catch (ArithmeticException e) {
                    System.out.println("You cannot divide by zero");
                    throw new ArithmeticException();
                }

        }

        return res;
    }
}
