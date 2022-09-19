package mylist;

import interfaces.MyList;
import nodes.Node;

import java.util.Arrays;

public class MyLinkedList<T> implements MyList<T> {
    private Node<T> first;
    private Node<T> last;
    private int size;

    public MyLinkedList() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    public MyLinkedList(T[] array) {
        if (array == null) {
            throw new IllegalArgumentException();
        }
        T[] buffer = Arrays.copyOf(array, array.length);
        for (int i = 0; i < buffer.length; i++) {
            add(buffer[i]);
        }
    }

    @Override
    public boolean add(T value) {
        return addLast(value);
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            return removeFirst();
        }

        if (index == size - 1) {
            return removeLast();
        }
        Node<T> nodeInIndex = getNodeInIndex(index);
        Node<T> previousNode = nodeInIndex.previous;
        Node<T> nextNode = nodeInIndex.next;
        previousNode.next = nextNode;
        nextNode.previous = previousNode;
        size--;
        return nodeInIndex.object;
    }

    @Override
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T get(int index) {
        return getNodeInIndex(index).object;
    }

    private boolean addLast(T value) {
        Node<T> l = last;
        Node<T> newNode = new Node<>(value, l, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        return true;
    }

    private T removeFirst() {
        T objInIndex = first.object;
        if (size > 1) {
            first = first.next;
            first.previous = null;
        } else {
            first = null;
            last = null;
        }
        size--;
        return objInIndex;
    }

    private T removeLast() {
        T objInIndex = last.object;
        last = last.previous;
        last.next = null;
        size--;
        return objInIndex;
    }

    private Node<T> getNodeInIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> nodeInIndex = first;
        for (int i = 0; i < index; i++) {
            nodeInIndex = nodeInIndex.next;
        }
        return nodeInIndex;
    }
}
