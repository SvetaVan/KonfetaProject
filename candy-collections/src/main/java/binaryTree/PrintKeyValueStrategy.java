package binaryTree;

public class PrintKeyValueStrategy implements ProcessElementStrategy {
    @Override
    public void processElement(BinaryTree.Node element) {
        System.out.print("key: "+element.getKey()+", value: "+element.getValue());
        System.out.println();
    }
}
