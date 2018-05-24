package binaryTree;

import java.util.ArrayList;
import java.util.Stack;

public class Fib {
    public static int fib(int n){
        if(n==0){
            return 0;
        }
        if (n==1){
            return 1;
        }else {
            return fib(n-1)+fib(n-2);
        }
    }

    public static void main(String[] args) {
        System.out.println(fib(5));
        Stack<Integer> stack = new Stack<>();
        char[] chars = {'c','b'};
        for(char c:chars){
            System.out.println(c);
        }
    }
}
