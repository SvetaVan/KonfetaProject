package binaryTree;

import com.google.common.base.Preconditions;

import java.util.Optional;

public class BinaryTree<K extends Comparable<K>, V> {
    private Node<K, V> root;

    public BinaryTree() {
        root = null;
    }

    public boolean add(K keyToAdd, V valueToAdd) {
        if (keyToAdd == null) {
            throw new NullPointerException("Element is null");
        }

        Node<K, V> newNode = new Node<>(keyToAdd, valueToAdd);
        if (root == null) {
            root = newNode;
            return true;
        }

        Node<K, V> currentNode = this.getRoot();
        Node<K, V> parent;
        while (true) {
            parent = currentNode;
            if (keyToAdd.compareTo(currentNode.getKey()) < 0) {
                currentNode = currentNode.getLeftChild();
                if (currentNode == null) {
                    parent.setLeftChild(newNode);
                    return true;
                }
            } else {
                currentNode = currentNode.getRightChild();
                if (currentNode == null) {
                    parent.setRightChild(newNode);
                    return true;
                }
            }
        }
    }

    public Optional<V> find(K keyToFind) {
        Preconditions.checkNotNull(keyToFind, "passed element is null");

        if (this.getRoot() == null) {
            return Optional.empty();
        }

        Node<K, V> currentNode = this.getRoot();

        while (keyToFind.compareTo(currentNode.getKey()) != 0) {
            if (keyToFind.compareTo(currentNode.getKey()) < 0) {
                currentNode = currentNode.getLeftChild();
            } else {
                currentNode = currentNode.getRightChild();
            }
            if (currentNode == null) {
                return Optional.empty();
            }
        }
        return Optional.ofNullable(currentNode.getValue());
    }


    // get strategy as argument and execute it, instead of sout
    public void traverseTree(Node<K, V> localRoot, ProcessElementStrategy strategy) {
        if (localRoot != null) {
            traverseTree(localRoot.getLeftChild(), strategy);
            strategy.processElement(localRoot);
            traverseTree(localRoot.getRightChild(), strategy);
        }
    }

    public boolean delete(K elementToDelete) {
        Preconditions.checkNotNull(elementToDelete,"passed parameter is null");
        if (this.getRoot() == null) {
            return false;
        }

        Node<K, V> currentNode = root;
        Node<K, V> parent = root;
        boolean isCurrentLeftChild = false;

        //searching for elementToDelete;
        while (elementToDelete.compareTo(currentNode.getKey()) != 0) {
            parent = currentNode;
            if (elementToDelete.compareTo(currentNode.getKey()) < 0) {
                currentNode = currentNode.getLeftChild();
                isCurrentLeftChild = true;
            } else {
                currentNode = currentNode.getRightChild();
                isCurrentLeftChild = false;
            }
        }

        // if we didn't find it
        if (currentNode == null) {
            return false;
        }

        // if there is no children
        if (currentNode.getRightChild() == null && currentNode.getLeftChild() == null) {
            if (currentNode == root) {
                root = null;
            }
            if (isCurrentLeftChild) {
                parent.setLeftChild(null);
            } else {
                parent.setRightChild(null);
            }
            return true;
        }

        //if there is 1 right child
        if (currentNode.getLeftChild() == null) {
            if (currentNode == root) {
                root = currentNode.getRightChild();
            }
            if (isCurrentLeftChild) {
                parent.setLeftChild(currentNode.getRightChild());
            } else {
                parent.setRightChild(currentNode.getRightChild());
            }
            return true;
        } else if (currentNode.getRightChild() == null) {
            if (currentNode == root) {
                root = currentNode.getLeftChild();
            }
            if (isCurrentLeftChild) {
                parent.setLeftChild(currentNode.getLeftChild());
            } else {
                parent.setRightChild(currentNode.getLeftChild());
            }
            return true;
        }

        //if there are 2 children
        return false;

    }


    /*private Node<K> getSuccessor(Node<K> nodeToDel) {
        Node<K> succesor;
        Node<K> current;
        succesor = findMin(nodeToDel.getRightChild());
        if(succesor!=nodeToDel.getRightChild()){
            succesor
        }


    }*/

    private Node<K, V> findMin(Node<K, V> nodeToStartSearch) {
        Node<K, V> current = nodeToStartSearch;
        Node<K, V> parent = nodeToStartSearch;
        while (current != null) {
            parent = current;
            current = current.getLeftChild();
        }
        return parent;
    }


    private Node<K, V> getRoot() {
        return root;
    }


    protected class Node<T extends Comparable<T>, E> {
        private T key;
        private E value;
        private Node<T, E> leftChild;
        private Node<T, E> rightChild;

        private Node(T key, E value) {
            this.key = key;
            this.value = value;
        }

        protected T getKey() {
            return key;
        }

        protected E getValue() {
            return value;
        }

        private Node<T, E> getLeftChild() {
            return leftChild;
        }

        private Node<T, E> getRightChild() {
            return rightChild;
        }

        private void setKey(T key) {
            this.key = key;
        }

        private void setValue(E value) {
            this.value = value;
        }

        private void setLeftChild(Node<T, E> leftChild) {
            this.leftChild = leftChild;
        }

        private void setRightChild(Node<T, E> rightChild) {
            this.rightChild = rightChild;
        }


    }

    public static void main(String[] args) {
        BinaryTree<Integer, String> binaryTree = new BinaryTree<>();
      /*  binaryTree.add(10, "c");
        binaryTree.add(5, "в");
        binaryTree.add(15, "е");
        binaryTree.add(7, "т");
        binaryTree.add(1, "а");
        binaryTree.traverseTree(binaryTree.getRoot(), new PrintKeyValueStrategy());


        System.out.println(binaryTree.find(10).orElseThrow(() -> new IllegalStateException("Нам нужен этот элемент!")));
        System.out.println(binaryTree.find(5555).orElse("it's default value"));
        if (binaryTree.find(101).isPresent()) {
            System.out.println("Есть");
        } else {
            System.out.println("Нет");
        }*/
        System.out.println(binaryTree.find(1));




    }
}

