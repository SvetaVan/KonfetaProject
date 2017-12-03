package myStructures;

import java.util.Iterator;

public class TestIterator {
    public static void main(String[] args) {
    MyLinkedList <Integer> myLinkedList = new MyLinkedList<>();
    myLinkedList.add(1);
    myLinkedList.add(2);
    myLinkedList.add(3);
    Iterator<Integer> integerIterator = myLinkedList.iterator();
    while(integerIterator.hasNext()){
        System.out.println(integerIterator.next());
    }
    }
}
