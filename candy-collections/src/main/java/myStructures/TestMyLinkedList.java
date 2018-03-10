package myStructures;

import java.util.*;

public class TestMyLinkedList {
    public static void main(String[] args) {
        //List<Integer> integers = new LinkedList<>(1, 2, 3, 4, 5);
        //List<Integer> integers1 = new MyLinkedList<>(1, 2, 3, 4, 5);
        //System.out.println(integers.retainAll(integers1));
    List<Integer> list  = new LinkedList<>();
    list.add(1);
    list.add(2);
    list.add(3);
    list.add(4);
    list.add(5);

    List<Integer> list1  = new LinkedList<>();

        System.out.println(list1.retainAll(list));
        System.out.println(list1.size());
    for(Integer i:list){
        System.out.println(i);
    }
    }

}




