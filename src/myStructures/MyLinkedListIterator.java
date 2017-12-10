package myStructures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedListIterator<T> implements Iterator<T> {
    private MyLinkedList.Node<T> currentNode;
    private MyLinkedList myLinkedList;

    public MyLinkedListIterator(MyLinkedList<T> myLinkedList) {
        currentNode = null;
        this.myLinkedList= myLinkedList;
    }

    @Override
    public boolean hasNext() {
        if(currentNode!=null&&currentNode.getNext()!=null){
            return true;
        }else if(myLinkedList.root!=null&&currentNode==null){
            return true;
        }

        return false;
    }

    @Override
    public T next() {
        if(currentNode==null){
            currentNode=myLinkedList.root;
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
        if(currentNode.getPrevious()==null){
            currentNode.getNext().setPrevious(null);
            myLinkedList.root=currentNode.getNext();

        }else if(currentNode.getNext()==null){
            currentNode.getPrevious().setNext(null);
        }else{
            currentNode.getPrevious().setNext(currentNode.getNext());
            currentNode.getNext().setPrevious(currentNode.getPrevious());
        }
    }

}
