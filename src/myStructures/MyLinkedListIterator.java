package myStructures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedListIterator <T> implements Iterator<T>{
    private MyLinkedList.Node<T> currentNode;

    public MyLinkedListIterator(MyLinkedList.Node<T> root){
        currentNode = root;
    }

    @Override
    public boolean hasNext() {
        if(currentNode!=null){
            return true;
        }
        return false;
    }

    @Override
    public T next() {
        T elementToReturn = currentNode.getCurrentElement();
        if(hasNext()){
        currentNode = currentNode.getNext();
        }else {
            throw new NoSuchElementException();
        }
        return elementToReturn;
    }

    //currentNode.previous.next = currentNode.next;
    @Override
    public void remove() {
        currentNode.getPrevious().setNext(currentNode.getNext());
        currentNode.getNext().setPrevious(currentNode.getPrevious());
    }

}
