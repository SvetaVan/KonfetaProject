package binaryTree;

import java.io.File;
import java.util.List;

public class Fibonacchi {
    public static int fib (int n){
        if(n==0){
            return 0;
        }
        if (n==1){
            return 1;
        }
        int count = 2;
        int current=1;
        int prev=0;
        int valueToReturn=0;
        while (count<=n){
            valueToReturn=current+prev;
            prev=current;
            current=valueToReturn;
            count++;
        }
        return valueToReturn;
    }


    public static void main(String[] args) {
        System.out.println(fib(4));
    }


}
