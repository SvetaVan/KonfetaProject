package binaryTree;

import org.junit.Test;

public class BinaryTreeTest {
    @Test
    public void binaryTreeTest (){
        BinaryTree<Integer,String> binaryTree = new BinaryTree<>();
        binaryTree.add(10,"с");
        binaryTree.add(5,"в");
        binaryTree.add(15,"е");
        binaryTree.add(7,"т");
        binaryTree.add(1,"а");


        System.out.println(binaryTree.find(10).orElseThrow(()->new IllegalStateException("Нам нужен этот элемент!")));
        System.out.println(binaryTree.find(5555).orElse("it's default value"));
        if(binaryTree.find(101).isPresent()){
            System.out.println("Есть");
        }else {
            System.out.println("Нет");
        }


        //System.out.println(binaryTree.findMin().getKey());

        // binaryTree.traverseTree(binaryTree.getRoot());
    }


}
