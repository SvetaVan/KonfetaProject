package myStructures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedListIterator<T> implements Iterator<T> {
    private MyLinkedList.Node<T> currentNode;
    private MyLinkedList.Node<T> root;

    public MyLinkedListIterator(MyLinkedList.Node<T> root) {
        currentNode = null;
        this.root= root;
    }

    @Override
    public boolean hasNext() {
        if(currentNode!=null&&currentNode.getNext()!=null){
            return true;
        }else if(root!=null&&currentNode==null){
            return true;
        }

        return false;
    }

    @Override
    public T next() {
        if(currentNode==null){
            currentNode=root;
        }else if(hasNext()){
            currentNode=currentNode.getNext();
        }else{
            throw new NoSuchElementException();
        }
        return currentNode.getCurrentElement();
    }

    //currentNode.previous.next = currentNode.next;
    @Override
    public void remove() {
        currentNode.getPrevious().setNext(currentNode.getNext());
        currentNode.getNext().setPrevious(currentNode.getPrevious());
    }

}
