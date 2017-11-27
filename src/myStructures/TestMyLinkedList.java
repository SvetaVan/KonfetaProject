package myStructures;

public class TestMyLinkedList {
    public static void main(String[] args) {
        System.out.println(testMyLinkedList());



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

}
