package binaryTree;

public class PrintKeyStrategy implements ProcessElementStrategy {
    @Override
    public void processElement(BinaryTree.Node element) {
        System.out.println(element.getKey());
    }
}
