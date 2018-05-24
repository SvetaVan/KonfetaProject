package binaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Validation2 {

    public static boolean validation(List<Character> characters) {
        if (characters == null || characters.size() % 2 != 0) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        List<Character> closingSymbols = new ArrayList<>();
        closingSymbols.add('}');
        closingSymbols.add(']');
        closingSymbols.add(')');
        for (Character character:characters) {
            if(!closingSymbols.contains(character)){
                stack.push(character);
            }else {
                if(stack.empty()){
                    return false;
                }
                Character opening = stack.pop();
                if(!validSequence(character,opening)){
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean validSequence(char closing, char opening) {
        switch (closing) {
            case ')':
                return opening == '(';
            case ']':
                return opening == '[';
            case '}':
                return opening == '{';
        }
        return false;
    }




}
