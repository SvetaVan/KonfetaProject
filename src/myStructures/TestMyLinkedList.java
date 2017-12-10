package myStructures;

import java.util.ArrayList;
import java.util.List;

public class TestMyLinkedList {
    public static void main(String[] args) {
        removeTest();


    }

    public static String testMyLinkedList (){
        MyLinkedList <Integer> list = new MyLinkedList<>();
        if (list.size()!=0){
            return "при проверки на нулевое количество элементов - не возвращает 0";
        }

        list.add(2);

        if (list.size()!=1){
            return "если в коллекции один элемент - не возвращает размер 1";
        }

        list.add(2);
        list.add(2);

        if (list.size()!=3){
            return "если в коллекции 3 элемента - не возвращает размер 3";
        }
        if(list.isEmpty()!=false){
            return "коллекция не пустая, но возвращает 0";
        }
        return "прошло проверку";
    }

    public static void removeTest (){
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();
        myLinkedList.add(1);
        myLinkedList.add(2);
        myLinkedList.add(3);
        myLinkedList.add(4);
        myLinkedList.remove((Object)1);
        for (Integer integer:myLinkedList) {
            System.out.println(integer);
        }
    }


}
