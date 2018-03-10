package myStructures;

import com.google.common.base.Preconditions;

import java.util.*;

public class MyLinkedList<T> extends MyAbstractList<T> {
    private Node<T> root;
    private Node<T> last;


    public MyLinkedList() {
    }

    private MyLinkedList(Node<T> root, Node<T> last, int size) {
        this.root = root;
        this.last = last;
        this.size = size;
    }

    @SafeVarargs
    public MyLinkedList(T... toAdd) {
        for (T element : toAdd) {
            add(element);
        }
    }

    //tested
    @Override
    public Iterator<T> iterator() {
        return new MyListIterator();
    }
    //tested
    @Override
    public boolean add(T t) {
        Preconditions.checkNotNull(t, "null elements are not permitted");
        size++;
        if (root == null) {
            root = new Node<>(null, null, t);
            last = root;
            return true;
        } else {
            Node<T> newNode = new Node<>(last, null, t);
            last.next = newNode;
            last = newNode;
            return true;
        }
    }

    //tested
    @Override
    public boolean remove(Object o) {
        Preconditions.checkNotNull(o, "the parameter for search is null");
        if (root != null && o.getClass() != this.root.element.getClass()) {
            throw new ClassCastException("parameter type = " + o.getClass() + ", collection element type = " + this.root.element.getClass());
        }
        Iterator<T> iterator = this.iterator();
        while (iterator.hasNext() && size != 0) {
            T currentObj = iterator.next();
            if (currentObj.equals(o)) {
                iterator.remove();
                //size--;  уменьшает размер в методе remove
                return true;
            }
        }
        return false;
    }

    //tested
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (T element : this) {
            stringBuilder.append(element);
            stringBuilder.append(", ");
        }
        if (stringBuilder.length() > 2) {
            stringBuilder.setLength(stringBuilder.length() - 2);
        }
        return "[" + stringBuilder + "]";
    }

    //tested
    @Override
    public T get(int i) {
        Iterator<T> iterator = this.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            if (i == count) {
                return iterator.next();
            }
            iterator.next();
            count++;
        }
        throw new IndexOutOfBoundsException("передан некорректный параметр элемента, возможные значения от 0 до " + (this.size - 1));
    }

    private Node<T> findNode(int index) {
        Node<T> currentElement = root;
        if (index >= size) {
            throw new IndexOutOfBoundsException(String.format("Too big index %s. Maximum: %s", index, size - 1));
        }
        for (int i = 0; i < index; i++) {
            currentElement = currentElement.next;
        }
        return currentElement;
    }
    //tested
    @Override
    public void add(int index, T element) {
        // Если нам передан индекс после последнего элемента, это значит что мы хотим добавить в конец
        if (index == size) {
            add(element);
            return;
        }

        Node<T> nextNode = findNode(index);
        //записываем предыдущий элемент
        Node<T> previousNode = nextNode.getPrevious();

        Node<T> newNode = new Node<>(null, null, element);

        // Связываем новый элемент со следующим
        newNode.setNext(nextNode);
        nextNode.setPrevious(newNode);

        // Cвязываем новый элемент с предыдущим, если такой существует (предыдуим может оказаться первый)
        if (previousNode != null) {
            previousNode.setNext(newNode);
            newNode.setPrevious(previousNode);
        }

        // Если мы добавляем в начало, не забудем и root обновить!
        if (index == 0) {
            root = newNode;
        }

        size++;
    }

    //tested
    @Override
    public T remove(int i) {
        if (i > this.size || i < 0) {
            throw new IndexOutOfBoundsException("incorrect element index, index is: " + i + " size of the list is: " + this.size);
        }
        T deletedElement = this.get(i);
        this.remove(this.get(i));
        return deletedElement;
    }

    //tested
    @Override
    public T set(int i, T t) {
        if (t != null) {
            T replacedElement = this.get(i);
            this.findNode(i).setElement(t);
            return replacedElement;
        } else {
            throw new NullPointerException("the element to be placed is null");
        }
    }

    //tested
    @Override
    public java.util.ListIterator<T> listIterator() {
        return new MyListIterator();
    }

    //tested
    @Override
    public java.util.ListIterator<T> listIterator(int i) {
        return new MyListIterator(i);
    }

    //tested
    @Override
    public List<T> subList(int i, int i1) {
        return Collections.unmodifiableList(new MyLinkedList<>(findNode(i), findNode(i1 - 1), i1 - i));
    }


    private static class Node<V> {
        private Node<V> previous;
        private Node<V> next;
        private V element;

        private Node() {
        }

        Node(Node<V> previous, Node<V> next, V element) {
            this.previous = previous;
            this.next = next;
            this.element = element;
        }

        @Override
        public boolean equals(Object otherObj) {
            if (this == otherObj) {
                return true;
            }
            if (otherObj == null) {
                return false;
            }
            if (this.getClass() != otherObj.getClass()) {
                return false;
            }
            Node<V> other = (Node<V>) otherObj;
            return this.element.equals(other.element);
        }

        @Override
        public int hashCode() {
            return element.hashCode();
        }

        Node<V> getNext() {
            return next;
        }

        Node<V> getPrevious() {
            return previous;
        }


        V getCurrentElement() {
            return element;
        }

        void setPrevious(Node<V> node) {
            this.previous = node;
        }

        void setNext(Node<V> node) {
            this.next = node;
        }

        void setElement(V element) {
            this.element = element;
        }

    }

    private class MyListIterator implements java.util.ListIterator<T> {
        private MyLinkedList.Node<T> lastReturned;
        int currentIndex = -1;

        public MyListIterator() {
            lastReturned = null;
        }

        public MyListIterator(int firstElementToBeReturnedByNext) {
            if (firstElementToBeReturnedByNext < 0 || firstElementToBeReturnedByNext > MyLinkedList.this.size()) {
                throw new IndexOutOfBoundsException("please check the required index");
            }
            if (firstElementToBeReturnedByNext > 0) {
                lastReturned = findNode(firstElementToBeReturnedByNext - 1);
                currentIndex = firstElementToBeReturnedByNext - 1;
            } else {
                lastReturned = null;
            }
        }

        @Override
        public boolean hasNext() {
            if (root == null) {
                return false;
            } else if ((lastReturned == null && currentIndex < size())
                    || lastReturned != last) {
                return true;
            }
            return false;
        }

        @Override
        public T next() {
            if (lastReturned == null) {
                lastReturned = root;
            } else if (hasNext()) {
                lastReturned = lastReturned.getNext();
            } else {
                throw new NoSuchElementException("В коллекции больше нет элементов");
            }
            ++currentIndex;
            return lastReturned.getCurrentElement();
        }

        @Override
        public boolean hasPrevious() {
            if (currentIndex >= 0) {
                return true;
            }
            return false;
        }

        @Override
        public T previous() {
            if (hasPrevious()) {
                currentIndex--;
                T elementToReturn = lastReturned.getCurrentElement();
                if (lastReturned.getPrevious() != null) {
                    lastReturned = lastReturned.getPrevious();
                }
                return elementToReturn;
            } else {
                throw new NoSuchElementException("такого элемента нет, вышли за пределы коллекции");
            }
        }

        @Override
        public int nextIndex() {
            if (lastReturned == null) {
                return 0;
            } else if (currentIndex < MyLinkedList.this.size - 1) {

                return currentIndex + 1;
            } else {
                return MyLinkedList.this.size;
            }
        }

        @Override
        public int previousIndex() {
            if (lastReturned == null) {
                return -1;
            } else
                return currentIndex >= 0 ? currentIndex : -1;
        }

        @Override
        public void remove() {
            if (lastReturned == null) {
                throw new IllegalStateException("не были предварительно вызваны next или previous");
            }
            if (lastReturned.getPrevious() == null && lastReturned.getNext() == null) {
                root = null;
                last = null;
            } else if (lastReturned.getPrevious() == null) {
                lastReturned.getNext().setPrevious(null);
                root = lastReturned.getNext();
            } else if (lastReturned == last) {
                last = lastReturned.getPrevious();
                lastReturned.getPrevious().setNext(null);
            } else {
                lastReturned.getPrevious().setNext(lastReturned.getNext());
                lastReturned.getNext().setPrevious(lastReturned.getPrevious());
            }
            --size;
        }


        @Override
        public void set(Object o) {
            if (lastReturned.getCurrentElement().getClass() != o.getClass()) {
                throw new ClassCastException("incompatible types with parameter\\n\" +\n" +
                        lastReturned.getCurrentElement().getClass() + " doesn't equal to " + o.getClass());
            }
            if (o != null) {
                lastReturned.setElement((T) o);
            } else {
                throw new NullPointerException("the element to be placed is null");
            }
        }

        @Override
        public void add(T t) {
            Preconditions.checkNotNull(t, "null elements are not permitted");
            Node<T> newNode = new Node<>(null, null, t);

            //добавляем первый элемент и обновляем root
            if (root == null) {
                root = newNode;
                last = root;
            }

            // если позиция на которую добавляем <size-1, то обновляем для NewNode предыдущий и следующий.
            if (currentIndex < MyLinkedList.this.size - 1) {
                Node<T> previousNode = findNode(currentIndex);
                Node<T> nextNode = previousNode.getNext();

                newNode.setPrevious(previousNode);
                newNode.setNext(nextNode);

                // для предыдущей и следующей ноды отновляем ссылки на новый элемент
                previousNode.setNext(newNode);
                nextNode.setPrevious(newNode);

                //обновляем текущий индекс и текущую ноду
                //++currentIndex;
                next();
                //urrentNode = currentNode.getNext();
            }

            //если добавляем на последнюю позицию
            if (currentIndex == MyLinkedList.this.size - 1) {
                newNode.setPrevious(last);
                //обновляем ссылку на последний элемент
                last.next = newNode;
                //последний добавленный элемент является last
                last = newNode;

                //обновляем текущий индекс и текущую ноду
                ++currentIndex;
                lastReturned = newNode;
            }
            size++;
        }
    }
}


