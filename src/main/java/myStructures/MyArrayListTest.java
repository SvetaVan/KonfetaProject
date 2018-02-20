package myStructures;

import java.util.*;

public class MyArrayListTest {
    public static void main(String[] args) {
        MyArrayList<Integer> myArrayList = new MyArrayList<>(2);
        myArrayList.add(1);
        myArrayList.add(2);
        myArrayList.add(3);
        myArrayList.add(4);
        myArrayList.add(5);
        myArrayList.add(6);
        List<Integer> arrayNew = myArrayList.subList(1,3);

        for (int i = 0; i <arrayNew.size() ; i++) {
            System.out.println(arrayNew.get(i));
        }

    }
}
