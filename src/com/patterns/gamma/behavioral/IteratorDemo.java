package com.patterns.gamma.behavioral;

/*
* Iteration is a core features of various data structures that facilitates the traversal of elements
* within that data structure.
* */

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

class Node<T> {
    public T value;
    public Node<T> left, right, parent;

    public Node(T value) {
        this.value = value;
    }

    public Node(T value, Node<T> left, Node<T> right) {
        this.value = value;
        this.left = left;
        this.right = right;

        left.parent = right.parent = this;
    }
}

class InOrderIterator<T> implements Iterator<T> {
    private Node<T> current, root;
    private boolean yieldedStart;

    public InOrderIterator(Node<T> root) {
        this.root = current = root;

        // We need to find the left most element. Traversing the In Order tree does not start at the root
        // of the tree but rather at the left most element e.g.:
        //   1
        //  / \
        // 2   3
        // Output: 213
        while (current.left != null) {
            current = current.left;
        }
    }

    private boolean hasRightMostParent(Node<T> node) {
        if (node.parent == null) {
            return false;
        }
        else {
            return (node == node.parent.left)
                    || hasRightMostParent(node.parent);
        }
    }

    @Override
    public boolean hasNext() {
        return current.left != null
                || current.right != null
                || hasRightMostParent(current);
    }

    @Override
    public T next()
    {
        if (!yieldedStart)
        {
            yieldedStart = true;
            return current.value;
        }

        if (current.right != null)
        {
            current = current.right;
            while (current.left != null) {
                current = current.left;
            }
            return current.value;
        }
        else
        {
            Node<T> parent = current.parent;
            while (parent != null && current == parent.right)
            {
                current = parent;
                parent = parent.parent;
            }
            current = parent;
            return current.value;
        }
    }
}

class BinaryTree<T> implements Iterable<T> {
    private Node<T> root;

    public BinaryTree(Node<T> root) {
        this.root = root;
    }

    @Override
    public Iterator<T> iterator() {
        return new InOrderIterator<>(root);
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        for (T item : this) {
            action.accept(item);
        }
    }

    @Override
    public Spliterator<T> spliterator() {
        return null;
    }
}

class IteratorDemo {
    public static void main(String[] args) {
        Node<java.lang.Integer> root =
                new Node<>(1,
                new Node<>(2),
                new Node<>(3));

        InOrderIterator<java.lang.Integer> iterator = new InOrderIterator<>(root);
        while (iterator.hasNext()) {
            System.out.println("" + iterator.next() + ",");
        }
        System.out.println();

        BinaryTree<java.lang.Integer> tree = new BinaryTree<>(root);
        for (int n : tree) {
            System.out.println("" + n + ",");
        }
        System.out.println();
    }
}
