package myStructures;

import java.util.*;

public class MyArrayListTest {
    public static void main(String[] args) {
        MyArrayList<Integer> myArrayList = new MyArrayList<>();
        myArrayList.add(1);
        myArrayList.add(2);
        myArrayList.add(3);
        myArrayList.add(4);

        List<Integer> integerList = myArrayList.subList(4,4);
        for (int i = 0; i <integerList.size() ; i++) {
            System.out.println(integerList.get(i));
        }






        /*ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);

        List<Integer> integerList = arrayList.subList(4,4);
        for (int i = 0; i <integerList.size() ; i++) {
            System.out.println(integerList.get(i));
        }
*/



    }
}
