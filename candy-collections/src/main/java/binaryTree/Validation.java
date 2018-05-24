package binaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Validation {

    public static boolean bracketsValidation(char[] charsToValidate) {
        //Does array contain even quantity of chars?
        if (charsToValidate.length % 2 != 0 || charsToValidate.length == 0) {
            return false;
        }

        //Does array contain invalid chars?
        if (!areValidCharacters(charsToValidate)) {
            return false;
        }

        Stack<Character> stack = new Stack<>();
        int current = -1;
        int previous;
        int i = 0;
        while (i < charsToValidate.length) {
            stack.push(charsToValidate[i]);
            current++;
            previous = current - 1;

            if (current > 0 && isValidCombination(stack.elementAt(current), stack.elementAt(previous))) {
                stack.pop();
                stack.pop();
                current = current - 2;
            }
            i++;
        }
        return stack.empty();
    }

    public static boolean areValidCharacters(char[] charsToValidate) {
        List<Character> listOfValidChars = new ArrayList<>();
        listOfValidChars.add('(');
        listOfValidChars.add(')');
        listOfValidChars.add('[');
        listOfValidChars.add(']');
        listOfValidChars.add('{');
        listOfValidChars.add('}');

        boolean validArray = true;
        for (char c : charsToValidate) {
            if (!listOfValidChars.contains(c)) {
                validArray = false;
            }
        }
        return validArray;
    }


    public static boolean isValidCombination(char current, char previous) {
        char validPrev = '\u0000'; //инициализация по умолчанию
        switch (current) {
            case ']':
                validPrev = '[';
                break;
            case '}':
                validPrev = '{';
                break;
            case ')':
                validPrev = '(';
                break;
        }
        return validPrev == previous;
    }


}
